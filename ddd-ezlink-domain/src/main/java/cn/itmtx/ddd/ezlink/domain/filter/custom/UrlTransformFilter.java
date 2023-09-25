package cn.itmtx.ddd.ezlink.domain.filter.custom;

import cn.itmtx.ddd.ezlink.domain.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.cache.UrlMapCacheManager;
import cn.itmtx.ddd.ezlink.domain.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.enums.TransformStatusEnum;
import cn.itmtx.ddd.ezlink.domain.filter.TransformFilter;
import cn.itmtx.ddd.ezlink.domain.filter.TransformFilterChain;
import cn.itmtx.ddd.ezlink.domain.gateway.UrlMapGateway;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 短链转换过滤器，核心工作是：
 *      context.setParam(TransformContext.PARAM_KEY_SHORT_URL, urlMapDO.getShortUrl());
 *      context.setParam(TransformContext.PARAM_KEY_LONG_URL, urlMapDO.getLongUrl());
 */
@Component
@Slf4j
public class UrlTransformFilter implements TransformFilter {

    @Autowired
    private UrlMapCacheManager urlMapCacheManager;

    @Override
    public int order() {
        return 2;
    }

    @Override
    public void init(TransformContext context) {
        TransformFilter.super.init(context);
    }

    @Override
    public void doFilter(TransformFilterChain chain, TransformContext context) {
        String compressionCode = context.getCompressionCode();
        // 根据压缩码找到映射记录
        UrlMapDO urlMapDO = urlMapCacheManager.loadUrlMapFromCache(compressionCode);
        if (Objects.isNull(urlMapDO)) {
            context.setTransformStatus(TransformStatusEnum.TRANSFORM_FAIL.getValue());
            log.warn("CompressionCode:[{}] not exists or occurred exception. Execution of the TransformFilterChain is terminated......", compressionCode);
            throw new BizException(String.format("CompressionCode:[%s] not exists or occurred exception.", compressionCode));
        }

        context.setTransformStatus(TransformStatusEnum.TRANSFORM_SUCCESS.getValue());
        context.setParam(TransformContext.PARAM_KEY_SHORT_URL, urlMapDO.getShortUrl());
        context.setParam(TransformContext.PARAM_KEY_LONG_URL, urlMapDO.getLongUrl());

        chain.doFilter(context);
    }
}
