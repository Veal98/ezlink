package cn.itmtx.ddd.ezlink.domain.domainservice;

import cn.itmtx.ddd.ezlink.client.dto.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.component.keygen.SequenceGenerator;
import cn.itmtx.ddd.ezlink.component.dl.lock.DistributedLockFactory;
import cn.itmtx.ddd.ezlink.domain.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.domain.cache.UrlMapCacheManager;
import cn.itmtx.ddd.ezlink.domain.constant.UrlValidatorConstant;
import cn.itmtx.ddd.ezlink.domain.enums.CompressionCodeStatusEnum;
import cn.itmtx.ddd.ezlink.domain.DomainConfDO;
import cn.itmtx.ddd.ezlink.domain.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.assembler.UrlMapDOAssembler;
import cn.itmtx.ddd.ezlink.domain.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.enums.LockKeyEnum;
import cn.itmtx.ddd.ezlink.domain.filter.TransformFilterChain;
import cn.itmtx.ddd.ezlink.domain.filter.TransformFilterChainFactory;
import cn.itmtx.ddd.ezlink.domain.gateway.CompressionCodeGateway;
import cn.itmtx.ddd.ezlink.domain.gateway.DomainConfGateway;
import cn.itmtx.ddd.ezlink.domain.gateway.UrlMapGateway;
import cn.itmtx.ddd.ezlink.domain.util.ConversionUtils;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UrlMapDomain {

    @Resource(name = "${ezlink.generate.sequence-generator.type}SequenceGenerator")
    private SequenceGenerator sequenceGenerator;

    private final UrlValidator urlValidator = new UrlValidator(new String[]{UrlValidatorConstant.HTTP_PROTOCOL,
            UrlValidatorConstant.HTTPS_PROTOCOL});

    /**
     * 压缩码生成策略
     */
    @Value("${ezlink.generate.sequence-generator.type}")
    private String strategy;

    @Value("${ezlink.generate.compression-code.batch}")
    private Integer compressionCodeGenerateBatchNum;

    @Value("${ezlink.default.domain}")
    private String defaultDomain;

    @Autowired
    private CompressionCodeGateway compressionCodeGateway;

    @Autowired
    private DomainConfGateway domainConfGateway;

    @Autowired
    private UrlMapGateway urlMapGateway;

    @Autowired
    private UrlMapDOAssembler urlMapDOAssembler;

    @Autowired
    private DistributedLockFactory distributedLockFactory;

    @Autowired
    private TransformFilterChainFactory transformFilterChainFactory;

    @Autowired
    private UrlMapCacheManager urlMapCacheManager;

    /**
     * 创建短链映射
     * @param urlMapAddCmd
     * @return
     */
    public UrlMapDTO createUrlMap(UrlMapAddCmd urlMapAddCmd) {
        RLock lock = distributedLockFactory.getLock(LockKeyEnum.CREATE_URL_MAP.getCode());
        try {
            lock.lock(LockKeyEnum.CREATE_URL_MAP.getReleaseTime(), TimeUnit.MILLISECONDS);
            UrlMapDO urlMapDO = new UrlMapDO();

            String longUrl = urlMapAddCmd.getLongUrl();
            Assert.isTrue(urlValidator.isValid(longUrl), String.format("长链接 [%s] 非法", longUrl));
            urlMapDO.setLongUrl(longUrl);

            urlMapDO.setDescription(urlMapAddCmd.getDescription());

            // 获取压缩码
            CompressionCodeDO compressionCodeDO = this.getAvailableCompressionCodeDO();
            Assert.isTrue(Objects.nonNull(compressionCodeDO) &&
                    CompressionCodeStatusEnum.AVAILABLE.getValue().equals(compressionCodeDO.getCodeStatus()), "compression code is not exits or is used");

            String compressionCode = compressionCodeDO.getCompressionCode();
            urlMapDO.setCompressionCode(compressionCode);

            // 生成短链
            String shortUrl = generateShortUrl(compressionCode);
            urlMapDO.setShortUrl(shortUrl);

            // 插入表 url_map + 更新表 compression_code
            compressionCodeDO.setCodeStatus(CompressionCodeStatusEnum.USED.getValue());
            this.saveUrlMapAndUpdateCompressCode(urlMapDO, compressionCodeDO);

            // 刷新缓存
            urlMapCacheManager.refreshUrlMapCache(urlMapDO);

            // UrlMapDO -> UrlMapDTO
            return urlMapDOAssembler.toUrlMapDTO(urlMapDO);
        } finally {
            lock.unlock();
        }
    }


    /**
     * 保存短链映射 + 更新压缩码状态
     * @param urlMapDO
     * @param compressionCodeDO
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveUrlMapAndUpdateCompressCode(UrlMapDO urlMapDO, CompressionCodeDO compressionCodeDO) {
        // 插入表 url_map
        urlMapGateway.insertUrlMapDO(urlMapDO);
        // 更新表 compression_code
        compressionCodeGateway.updateByPrimaryKeySelective(compressionCodeDO);
    }

    /**
     * 处理 URL 转换
     * @param context
     */
    public void processTransform(TransformContext context) {
        long start = System.nanoTime();
        if (log.isDebugEnabled()) {
            log.debug("Start ProcessTransform...");
        }
        // 构建过滤器链
        TransformFilterChain chain = transformFilterChainFactory.buildTransformFilterChain(context);
        try {
            chain.doFilter(context);
        } finally {
            chain.release();
            context.release();
            if (log.isDebugEnabled()) {
                log.debug("End ProcessTransform,cost {} ms...", TimeUnit.NANOSECONDS.toMillis((System.nanoTime() - start)));
            }
        }
    }

    /**
     * 批量生成 62 进制压缩码
     */
    private void generateBatchCompressionCode() {
        for (int i = 0; i < compressionCodeGenerateBatchNum; i ++) {
            CompressionCodeDO compressionCodeDO = new CompressionCodeDO();
            compressionCodeDO.setStrategy(strategy);
            // 生成 10 进制压缩码
            long sequence = sequenceGenerator.generate();
            compressionCodeDO.setSequenceValue(String.valueOf(sequence));
            // 10 进制转 62 进制
            String code = ConversionUtils.X.encode62(sequence);
            compressionCodeDO.setCompressionCode(code);

            // 存入数据库表 `compression_code`
            compressionCodeGateway.insertCompressionCodeDO(compressionCodeDO);
        }
    }

    /**
     * 获取一个可用的压缩码
     * @return
     */
    private CompressionCodeDO getAvailableCompressionCodeDO() {
        CompressionCodeDO compressionCodeDO = compressionCodeGateway.getLatestAvailableCompressionCodeDO();
        if (Objects.nonNull(compressionCodeDO)) {
            return compressionCodeDO;
        }

        generateBatchCompressionCode();
        return compressionCodeGateway.getLatestAvailableCompressionCodeDO();
    }

    /**
     * 生成 shorturl
     * @param compressionCode
     * @return
     */
    private String generateShortUrl(String compressionCode) {
        DomainConfDO domainConfDO = domainConfGateway.getDomainConfDOByDomainValue(defaultDomain);
        String protocol = domainConfDO.getProtocol();
        return String.format("%s://%s/%s", protocol, defaultDomain, compressionCode);
    }
}
