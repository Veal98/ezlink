package cn.itmtx.ddd.ezlink.component.keygen;

import com.google.common.hash.BloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
     * @param longUrl
     * @return
     */
    public SequenceAndCode generate(String longUrl) {
        // 生成 10 进制压缩码
        long sequence = this.generateSequence(longUrl);

        // 10 进制转 62 进制
        String compressionCode = ConversionUtils.X.encode62(sequence, compressionCodeLength);

        // TODO 处理哈希冲突

        // 存到 BloomFilter
        compressionCodeBloom.put(compressionCode);
        log.info("The compressionCodes [{}] has stored in bloomFilter.", compressionCode);

        SequenceAndCode sequenceAndCode = new SequenceAndCode();
        sequenceAndCode.setSequence(String.valueOf(sequence));
        sequenceAndCode.setCompressionCode(compressionCode);
        return sequenceAndCode;
    }

    /**
     * 生成 10 进制压缩码
     * @param longUrl
     * @return
     */
    public abstract long generateSequence(String longUrl);
}
