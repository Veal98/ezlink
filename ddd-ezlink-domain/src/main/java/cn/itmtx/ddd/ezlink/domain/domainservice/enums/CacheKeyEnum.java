package cn.itmtx.ddd.ezlink.domain.domainservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum CacheKeyEnum {


    /**
     * 可访问的压缩码映射
     * 暂定设置用不过期
     */
    ACCESS_CODE_HASH("ezlink:access:code:hash", "可访问的压缩码映射", -1L),

    ;

    private final String key;
    private final String description;
    private final long expireSeconds;

    CacheKeyEnum(String key, String description, long expireSeconds) {
        this.key = key;
        this.description = description;
        this.expireSeconds = expireSeconds;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public long getExpireSeconds() {
        return expireSeconds;
    }
}
