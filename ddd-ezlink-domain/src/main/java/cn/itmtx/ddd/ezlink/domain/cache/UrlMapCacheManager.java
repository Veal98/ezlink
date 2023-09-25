package cn.itmtx.ddd.ezlink.domain.cache;

import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.domain.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.enums.CacheKeyEnum;
import cn.itmtx.ddd.ezlink.domain.gateway.UrlMapGateway;
import com.google.gson.Gson;
import jodd.cache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UrlMapCacheManager {

    @Autowired
    private UrlMapGateway urlMapGateway;

    private final StringRedisTemplate stringRedisTemplate;

    public UrlMapCacheManager(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 刷新 UrlMap 缓存
     * @param urlMapDO
     */
    public void refreshUrlMapCache(UrlMapDO urlMapDO) {
        if (Objects.isNull(urlMapDO)) {
            return ;
        }

        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        hashOperations.put(CacheKeyEnum.ACCESS_CODE_HASH.getKey(), urlMapDO.getCompressionCode(), new Gson().toJson(urlMapDO));
    }

    /**
     * 根据 compressionCode 从缓存中获取 UrlMapDO
     * 如果缓存中没获取到，就从数据库中获取
     * @param compressionCode
     * @return
     */
    public UrlMapDO loadUrlMapFromCache(String compressionCode) {
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String hv = hashOperations.get(CacheKeyEnum.ACCESS_CODE_HASH.getKey(), compressionCode);
        UrlMapDO urlMapDO = StringUtils.isNotEmpty(hv) ? new Gson().fromJson(hv, UrlMapDO.class) : loadUrlMapFromDb(compressionCode);
        return Objects.isNull(urlMapDO) ? null : urlMapDO;
    }


    private UrlMapDO loadUrlMapFromDb(String compressionCode) {
        // 根据压缩码查找数据库
        UrlMapDO urlMapDO = urlMapGateway.getUrlMapDOByCompressionCode(compressionCode);
        if (Objects.isNull(urlMapDO)) {
            // TODO 这里需要防止缓存击穿
        }

        // 刷新缓存
        this.refreshUrlMapCache(urlMapDO);
        return urlMapDO;
    }
}
