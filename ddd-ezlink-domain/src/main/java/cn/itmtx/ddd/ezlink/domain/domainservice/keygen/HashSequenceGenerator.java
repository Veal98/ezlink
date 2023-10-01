package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

import cn.itmtx.ddd.ezlink.domain.domainservice.util.ConversionUtils;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.security.SecureRandom;

/**
 * 基于哈希算法（采用 MurmurHash）生成 10 进制压缩码
 */
@Component
public class HashSequenceGenerator extends SequenceGenerator {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    public static final Integer RANDOM_STRING_LENGTH = 4;

    @Override
    public long generateSequence(String longUrl) {
        // MurmurHash 算法
        HashFunction hashFunction = Hashing.murmur3_32();
        HashCode hashCode = hashFunction.hashString(longUrl, Charset.forName("utf-8"));
        int sequence = Math.abs(hashCode.asInt());
        return sequence;
    }

    /**
     * 处理哈希冲突：在 longUrl 后面添加随机字符后再 Hash
     * @param longUrl
     * @param compressionCodeLength
     * @param compressionCodeBloom
     * @return
     */
    @Override
    public long fixConflict(String longUrl, Integer compressionCodeLength, BloomFilter<String> compressionCodeBloom) {
        String newLongUrl = longUrl;
        newLongUrl += this.randomString(RANDOM_STRING_LENGTH);
        // 10 进制压缩码
        long newSequence = this.generateSequence(newLongUrl);
        // 10 进制转 62 进制
        String newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
        // 判断是否在 BloomFilter 中
        boolean isInBloomFilter = compressionCodeBloom.mightContain(newCompressionCode);

        while(isInBloomFilter) {
            newLongUrl += this.randomString(RANDOM_STRING_LENGTH);
            // 10 进制压缩码
            newSequence = this.generateSequence(newLongUrl);
            // 10 进制转 62 进制
            newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
            isInBloomFilter = compressionCodeBloom.mightContain(newCompressionCode);
        }


        return newSequence;
    }

    /**
     * 发生哈希冲突时，生成随机字符串拼接在 longUrl 后面重新 hash
     * @param length 生成的字符串长度
     * @return
     */
    private String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        sb.append("[");
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        sb.append("]");
        return sb.toString();
    }
}
