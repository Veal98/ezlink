package cn.itmtx.ddd.ezlink.component.keygen;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * 基于哈希算法（采用 MurmurHash）生成 10 进制压缩码
 */
@Component
public class HashSequenceGenerator extends SequenceGenerator{
    @Override
    public long generateSequence(String longUrl) {
        // MurmurHash 算法
        HashFunction hashFunction = Hashing.murmur3_32();
        HashCode hashCode = hashFunction.hashString(longUrl, Charset.forName("utf-8"));


        return 0;
    }
}
