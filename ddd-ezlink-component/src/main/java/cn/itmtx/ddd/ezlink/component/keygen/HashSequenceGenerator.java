package cn.itmtx.ddd.ezlink.component.keygen;

import org.springframework.stereotype.Component;

/**
 * 基于哈希算法（采用 MurmurHash）生成 10 进制压缩码
 */
@Component
public class HashSequenceGenerator implements SequenceGenerator{
    @Override
    public long generate() {
        return 0;
    }
}
