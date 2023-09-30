package cn.itmtx.ddd.ezlink.component.keygen;

import org.springframework.stereotype.Component;

/**
 * 基于 ID 生成器（采用 snowflake 算法）生成 10 进制压缩码
 */
@Component
public class IdSequenceGenerator extends SequenceGenerator {
    @Override
    public long generateSequence(String longUrl) {
        return JavaSnowflake.nextId();
    }
}
