package cn.itmtx.ddd.ezlink.domain.domainservice.keygen;

import cn.itmtx.ddd.ezlink.component.bloomfilter.BloomFilterHelper;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.SequenceGeneratorStrategyEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.ConversionUtils;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.security.SecureRandom;

/**
 * @Date 2024/5/22
 * @Description
 **/
@Component
public class HashDecimalSequenceGenerator implements DecimalSequenceGenerator {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    public static final Integer RANDOM_STRING_LENGTH = 4;

    /**
     * 策略名称
     *
     * @return
     */
    @Override
    public String strategyName() {
        return SequenceGeneratorStrategyEnum.HASH.getStrategyName();
    }

    /**
     * 生成 10 进制压缩码
     *
     * @param longUrl
     * @return
     */
    @Override
    public long generateDecimalSequence(String longUrl) {
        // MurmurHash 算法
        HashFunction hashFunction = Hashing.murmur3_32();
        HashCode hashCode = hashFunction.hashString(longUrl, Charset.forName("utf-8"));
        int sequence = Math.abs(hashCode.asInt());
        return sequence;
    }

    /**
     * 处理冲突：在 longUrl 后面添加随机字符后再 Hash
     * @param longUrl
     * @param compressionCodeLength
     * @return
     */
    @Override
    public long fixConflict(String longUrl, Integer compressionCodeLength) {
        String newLongUrl = longUrl;
        newLongUrl += this.randomString(RANDOM_STRING_LENGTH);
        // 重新生成 10 进制压缩码
        long newSequence = this.generateDecimalSequence(newLongUrl);
        // 10 进制转 62 进制
        String newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
        // 判断是否在 BloomFilter 中
        boolean isInBloomFilter = BloomFilterHelper.mightContain(newCompressionCode);

        // bloomfiter 判断不在那么一定不在，判断存在不一定真的存在
        // 所以这里不一定是真的发生冲突了，可以查库看下是不是真的已经存在相同的压缩码。但是查库性能损耗大，不如直接重新生成一次压缩码来得快
        while(isInBloomFilter) {
            newLongUrl += this.randomString(RANDOM_STRING_LENGTH);
            // 重新生成10 进制压缩码
            newSequence = this.generateDecimalSequence(newLongUrl);
            // 10 进制转 62 进制
            newCompressionCode = ConversionUtils.X.encode62(newSequence, compressionCodeLength);
            isInBloomFilter = BloomFilterHelper.mightContain(newCompressionCode);
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
