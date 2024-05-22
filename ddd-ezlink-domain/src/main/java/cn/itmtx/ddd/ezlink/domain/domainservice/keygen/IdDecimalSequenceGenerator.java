package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

import cn.itmtx.ddd.ezlink.component.bloomfilter.BloomFilterHelper;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.SequenceGeneratorStrategyEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.ConversionUtils;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.JavaSnowflakeUtils;
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
     * @return
     */
    @Override
    public long fixConflict(String longUrl, Integer compressionCodeLength) {
        // 重新生成 10 进制压缩码
        long newSequence = this.generateDecimalSequence(longUrl);
        // 10 进制转 62 进制
        String newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
        // 判断是否在 BloomFilter 中
        boolean isInBloomFilter = BloomFilterHelper.mightContain(newCompressionCode);

        // bloomfiter 判断不在那么一定不在，判断存在不一定真的存在
        // 所以这里不一定是真的发生冲突了，可以查库看下是不是真的已经存在相同的压缩码。但是查库性能损耗大，不如直接重新生成一次压缩码来得快
        while(isInBloomFilter) {
            // 重新生成 10 进制压缩码
            newSequence = this.generateDecimalSequence(longUrl);
            // 10 进制转 62 进制
            newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
            isInBloomFilter = BloomFilterHelper.mightContain(newCompressionCode);
        }


        return newSequence;
    }
}
