package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

import cn.itmtx.ddd.ezlink.domain.domainservice.util.ConversionUtils;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.JavaSnowflakeUtils;
import com.google.common.hash.BloomFilter;
import org.springframework.stereotype.Component;

/**
 * 基于 ID 生成器（采用 snowflake 算法）生成 10 进制压缩码
 */
@Component
public class IdSequenceGenerator extends SequenceGenerator {
    @Override
    public long generateSequence(String longUrl) {
        return JavaSnowflakeUtils.nextId();
    }

    /**
     * 处理 hash 冲突：重新调用雪花算法
     * @param longUrl
     * @param compressionCodeLength
     * @param compressionCodeBloom
     * @return
     */
    @Override
    public long fixConflict(String longUrl, Integer compressionCodeLength, BloomFilter<String> compressionCodeBloom) {
        // 10 进制压缩码
        long newSequence = this.generateSequence(longUrl);
        // 10 进制转 62 进制
        String newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
        // 判断是否在 BloomFilter 中
        boolean isInBloomFilter = compressionCodeBloom.mightContain(newCompressionCode);

        while(isInBloomFilter) {
            // 10 进制压缩码
            newSequence = this.generateSequence(longUrl);
            // 10 进制转 62 进制
            newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
            isInBloomFilter = compressionCodeBloom.mightContain(newCompressionCode);
        }


        return newSequence;
    }
}
