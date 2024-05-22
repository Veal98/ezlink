package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

import cn.itmtx.ddd.ezlink.domain.domainservice.enums.SequenceGeneratorStrategyEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.ConversionUtils;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.JavaSnowflakeUtils;
import com.google.common.hash.BloomFilter;
import org.springframework.stereotype.Component;

/**
 * @Date 2024/5/22
 * @Description
 **/
@Component
public class IdDecimalSequenceGenerator implements DecimalSequenceGenerator{
    /**
     * 策略名称
     *
     * @return
     */
    @Override
    public String strategyName() {
        return SequenceGeneratorStrategyEnum.ID.getStrategyName();
    }

    /**
     * 生成 10 进制压缩码
     *
     * @param longUrl
     * @return
     */
    @Override
    public long generateDecimalSequence(String longUrl) {
        return JavaSnowflakeUtils.nextId();
    }

    /**
     * 处理冲突，返回不冲突的 10 进制压缩码
     *
     * @param longUrl
     * @param compressionCodeLength
     * @param compressionCodeBloom
     * @return
     */
    @Override
    public long fixConflict(String longUrl, Integer compressionCodeLength, BloomFilter<String> compressionCodeBloom) {
        // 重新生成 10 进制压缩码
        long newSequence = this.generateDecimalSequence(longUrl);
        // 10 进制转 62 进制
        String newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
        // 判断是否在 BloomFilter 中
        boolean isInBloomFilter = compressionCodeBloom.mightContain(newCompressionCode);

        while(isInBloomFilter) {
            // 重新生成 10 进制压缩码
            newSequence = this.generateDecimalSequence(longUrl);
            // 10 进制转 62 进制
            newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
            isInBloomFilter = compressionCodeBloom.mightContain(newCompressionCode);
        }


        return newSequence;
    }
}
