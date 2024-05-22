package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

import cn.itmtx.ddd.ezlink.component.bloomfilter.BloomFilterHelper;
import cn.itmtx.ddd.ezlink.domain.domainobject.SequenceAndCodeDO;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.SequenceGeneratorStrategyEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.ConversionUtils;
import lombok.extern.slf4j.Slf4j;
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

    private DecimalSequenceGenerator decimalSequenceGenerator;

    /**
     * 62 进制压缩码的长度
     */
    @Value("${ezlink.generate.compression-code.length}")
    private Integer compressionCodeLength;

    /**
     * 压缩码生成策略
     */
    @Value("${ezlink.generate.sequence-generator.type}")
    private String strategy;

    /**
     * 加载 10 进制压缩码生成器
     * @return
     */
    private DecimalSequenceGenerator loadGenerator() {
        if (Objects.nonNull(decimalSequenceGenerator)) {
            return decimalSequenceGenerator;
        }

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
        decimalSequenceGenerator = this.loadGenerator();

        // 生成 10 进制压缩码
        long sequence = decimalSequenceGenerator.generateDecimalSequence(longUrl);
        // 10 进制转 62 进制
        String compressionCode = ConversionUtils.X.encode62(sequence, compressionCodeLength);

        // 处理冲突
        boolean isInBloomFilter = BloomFilterHelper.mightContain(compressionCode);
        if (isInBloomFilter) {
            sequence = decimalSequenceGenerator.fixConflict(longUrl, compressionCodeLength);
            compressionCode = ConversionUtils.X.encode62(sequence, compressionCodeLength);
        }

        // compressionCode 存到 BloomFilter
        BloomFilterHelper.put(compressionCode);
        log.info("The compressionCodes [{}] has stored in bloomFilter.", compressionCode);

        // 返回 compressionCode 和 sequence
        SequenceAndCodeDO sequenceAndCodeDO = new SequenceAndCodeDO();
        sequenceAndCodeDO.setSequence(String.valueOf(sequence));
        sequenceAndCodeDO.setCompressionCode(compressionCode);
        return sequenceAndCodeDO;
    }
}
