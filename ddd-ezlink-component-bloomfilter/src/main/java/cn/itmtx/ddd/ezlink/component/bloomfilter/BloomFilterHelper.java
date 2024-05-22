package cn.itmtx.ddd.ezlink.component.bloomfilter;

import com.google.common.hash.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Author jc.yin
 * @Date 2024/5/22
 * @Description
 **/
@Component
public class BloomFilterHelper {

    private static BloomFilter<String> compressionCodeBloom;

    @Autowired
    public BloomFilterHelper(@Qualifier("compressionCodeBloom") BloomFilter<String> compressionCodeBloom) {
        BloomFilterHelper.compressionCodeBloom = compressionCodeBloom;
    }

    public static Boolean mightContain(String key) {
        return compressionCodeBloom.mightContain(key);
    }

    /**
     * TODO，BloomFilter 的数据需要持久化
     * @param key
     * @return
     */
    public static Boolean put(String key) {
        return compressionCodeBloom.put(key);
    }


}
