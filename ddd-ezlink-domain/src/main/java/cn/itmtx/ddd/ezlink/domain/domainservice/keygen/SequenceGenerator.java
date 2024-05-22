package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

import cn.itmtx.ddd.ezlink.domain.domainobject.SequenceAndCodeDO;
import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.domainservice.cache.UrlMapCacheManager;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.SequenceGeneratorStrategyEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.ConversionUtils;
import com.google.common.hash.BloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.ServiceLoader;

/**
 * 62 进制压缩码生成器
 */
@Component
@Slf4j
public class SequenceGenerator {

    private final static DecimalSequenceGenerator DECIMAL_SEQUENCE_GENERATOR;

    static {
        DECIMAL_SEQUENCE_GENERATOR = loadGenerator();
    }

    @Autowired
    @Qualifier("compressionCodeBloom")
    private BloomFilter<String> compressionCodeBloom;

    /**
     * 62 进制压缩码的长度
     */
    @Value("${ezlink.generate.compression-code.length}")
    private Integer compressionCodeLength;

    /**
     * 压缩码生成策略
     */
    @Value("${ezlink.generate.sequence-generator.type}")
    private static String strategy;

    /**
     * 加载 10 进制压缩码生成器
     * @return
     */
    private static DecimalSequenceGenerator loadGenerator() {
        SequenceGeneratorStrategyEnum strategyEnum = SequenceGeneratorStrategyEnum.findBy(strategy);
        // 如果找不到相应的 strategy 配置，则走 spi 机制查找用户自定义的 generator
        if (Objects.isNull(strategyEnum)) {
            // 加载定义的 DecimalSequenceGenerator 实现类
            ServiceLoader<DecimalSequenceGenerator> decimalSequenceGenerators = ServiceLoader.load(DecimalSequenceGenerator.class);
            // 根据 strategy 进行匹配
            for (DecimalSequenceGenerator decimalSequenceGenerator : decimalSequenceGenerators) {
                if (decimalSequenceGenerator.strategyName().equalsIgnoreCase(strategy)) {
                    return decimalSequenceGenerator;
                }
            }

            throw new IllegalArgumentException("not valid strategy, can't find valid DecimalSequenceGenerator.");
        } else {
            switch (strategyEnum) {
                case ID:
                    return new IdDecimalSequenceGenerator();
                case HASH:
                    return new HashDecimalSequenceGenerator();
                default:
                    throw new IllegalArgumentException("not valid strategy, can't find valid DecimalSequenceGenerator.");
            }
        }


    }

    /**
     * 生成 62 进制压缩码
     *  - 1. 先生成 10 进制
     *  - 2. 10 进制转 62 进制
     *  - 3. 若有冲突则处理冲突
     *  - 4. 将最终的 62 进制压缩码存入 BloomFilter
     * @param longUrl
     * @return
     */
    public SequenceAndCodeDO generate(String longUrl) {
        // 生成 10 进制压缩码
        long sequence = DECIMAL_SEQUENCE_GENERATOR.generateDecimalSequence(longUrl);
        // 10 进制转 62 进制
        String compressionCode = ConversionUtils.X.encode62(sequence, compressionCodeLength);

        // 处理冲突
        boolean isInBloomFilter = compressionCodeBloom.mightContain(compressionCode);
        if (isInBloomFilter) {
            sequence = DECIMAL_SEQUENCE_GENERATOR.fixConflict(longUrl, compressionCodeLength, compressionCodeBloom);
            compressionCode = ConversionUtils.X.encode62(sequence, compressionCodeLength);
        }

        // compressionCode 存到 BloomFilter
        compressionCodeBloom.put(compressionCode);
        log.info("The compressionCodes [{}] has stored in bloomFilter.", compressionCode);

        // 返回 compressionCode 和 sequence
        SequenceAndCodeDO sequenceAndCodeDO = new SequenceAndCodeDO();
        sequenceAndCodeDO.setSequence(String.valueOf(sequence));
        sequenceAndCodeDO.setCompressionCode(compressionCode);
        return sequenceAndCodeDO;
    }
}
