package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

import cn.itmtx.ddd.ezlink.domain.domainobject.SequenceAndCodeDO;
import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.domainservice.cache.UrlMapCacheManager;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.ConversionUtils;
import com.google.common.hash.BloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 62 进制压缩码生成器
 */
@Component
@Slf4j
public abstract class SequenceGenerator {

    @Autowired
    @Qualifier("compressionCodeBloom")
    private BloomFilter<String> compressionCodeBloom;

    /**
     * 62 进制压缩码的长度
     */
    @Value("${ezlink.generate.compression-code.length}")
    private Integer compressionCodeLength;

    /**
     * 10 进制转 62 进制
     *
     * @param longUrl
     * @return
     */
    public SequenceAndCodeDO generate(String longUrl) {
        // 生成 10 进制压缩码
        long sequence = this.generateSequence(longUrl);
        // 10 进制转 62 进制
        String compressionCode = ConversionUtils.X.encode62(sequence, compressionCodeLength);

        // 处理哈希冲突
        boolean isInBloomFilter = compressionCodeBloom.mightContain(compressionCode);
        if (isInBloomFilter) {
            sequence = this.fixConflict(longUrl, compressionCodeLength, compressionCodeBloom);
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

    /**
     * 生成 10 进制压缩码
     *
     * @param longUrl
     * @return
     */
    public abstract long generateSequence(String longUrl);

    /**
     * 处理哈希冲突，返回不冲突的 10 进制压缩码
     *
     * @param longUrl
     * @return
     */
    public abstract long fixConflict(String longUrl, Integer compressionCodeLength, BloomFilter<String> compressionCodeBloom);
}
