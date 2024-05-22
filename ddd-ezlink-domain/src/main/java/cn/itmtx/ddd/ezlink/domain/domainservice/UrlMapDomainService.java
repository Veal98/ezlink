package cn.itmtx.ddd.ezlink.domain.domainservice;

import cn.itmtx.ddd.ezlink.component.dl.lock.DistributedLockFactory;
import cn.itmtx.ddd.ezlink.domain.domainobject.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.domain.domainobject.DomainConfDO;
import cn.itmtx.ddd.ezlink.domain.domainobject.SequenceAndCodeDO;
import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.domainservice.cache.UrlMapCacheManager;
import cn.itmtx.ddd.ezlink.domain.domainservice.constant.UrlValidatorConstant;
import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.CompressionCodeStatusEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.LockKeyEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilterChain;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilterChainFactory;
import cn.itmtx.ddd.ezlink.domain.domainservice.keygen.SequenceGenerator;
import cn.itmtx.ddd.ezlink.domain.gateway.CompressionCodeGateway;
import cn.itmtx.ddd.ezlink.domain.gateway.DomainConfGateway;
import cn.itmtx.ddd.ezlink.domain.gateway.UrlMapGateway;
import com.alibaba.cola.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UrlMapDomainService {

    @Autowired
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
    private DistributedLockFactory distributedLockFactory;

    @Autowired
    private TransformFilterChainFactory transformFilterChainFactory;

    @Autowired
    private UrlMapCacheManager urlMapCacheManager;

    /**
     * 创建短链映射
     * @param urlMapDO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void createUrlMap(UrlMapDO urlMapDO) {
        RLock lock = distributedLockFactory.getLock(LockKeyEnum.CREATE_URL_MAP.getCode());
        try {
            lock.lock(LockKeyEnum.CREATE_URL_MAP.getReleaseTime(), TimeUnit.MILLISECONDS);

            String longUrl = urlMapDO.getLongUrl();
            Assert.isTrue(urlValidator.isValid(longUrl), String.format("长链接 [%s] 非法", longUrl));

            // 获取压缩码
            CompressionCodeDO compressionCodeDO = this.getAvailableCompressionCodeDO(longUrl);
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
            // TODO，此处可改为删除缓存，防止缓存不一致问题
            urlMapCacheManager.refreshUrlMapCache(urlMapDO);
        } finally {
            // 判断要解锁的key是否被当前线程持有,防止出现 not locked by current thread 异常
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
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
     * @param longUrl
     */
    private void generateBatchCompressionCode(String longUrl) {
        for (int i = 0; i < compressionCodeGenerateBatchNum; i ++) {
            CompressionCodeDO compressionCodeDO = new CompressionCodeDO();
            compressionCodeDO.setStrategy(strategy);
            // 生成 62 进制压缩码
            SequenceAndCodeDO sequenceAndCodeDO = sequenceGenerator.generate(longUrl);
            compressionCodeDO.setSequenceValue(sequenceAndCodeDO.getSequence());
            compressionCodeDO.setCompressionCode(sequenceAndCodeDO.getCompressionCode());

            // 存入数据库表 `compression_code`
            compressionCodeGateway.insertCompressionCodeDO(compressionCodeDO);
        }
    }

    /**
     * 获取一个可用的压缩码
     * @return
     */
    private CompressionCodeDO getAvailableCompressionCodeDO(String longUrl) {
        CompressionCodeDO compressionCodeDO = compressionCodeGateway.getLatestAvailableCompressionCodeDO();
        if (Objects.nonNull(compressionCodeDO)) {
            return compressionCodeDO;
        }

        generateBatchCompressionCode(longUrl);
        return compressionCodeGateway.getLatestAvailableCompressionCodeDO();
    }

    /**
     * 生成 shorturl
     * @param compressionCode
     * @return
     */
    private String generateShortUrl(String compressionCode) {
        DomainConfDO domainConfDO = domainConfGateway.getDomainConfDOByDomainValue(defaultDomain);
        if (Objects.isNull(domainConfDO) || StringUtils.isEmpty(domainConfDO.getProtocol())) {
            log.warn("domain not exists in db.");
            throw new SysException("domain not exists in db. can not generate short url");
        }
        String protocol = domainConfDO.getProtocol();
        return String.format("%s://%s/%s", protocol, defaultDomain, compressionCode);
    }
}
