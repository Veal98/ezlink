package cn.itmtx.ddd.ezlink.domain.domainservice.filter.custom;

import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilter;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilterChain;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.IpUtils;
import org.springframework.stereotype.Component;

/**
 * 提取 request header 中的信息封装到 TransformContext
 */
@Component
public class ExtractRequestHeaderTransformFilter implements TransformFilter {

    private static final String HEADER_KEY_UA = "User-Agent";

    private static final String HEADER_KEY_COOKIE = "Cookie";

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void init(TransformContext context) {

    }

    @Override
    public void doFilter(TransformFilterChain chain, TransformContext context) {
        try {
            context.setParam(TransformContext.PARAM_KEY_UA, context.getHeader(HEADER_KEY_UA));
            context.setParam(TransformContext.PARAM_KEY_COOKIE, context.getHeader(HEADER_KEY_COOKIE));
            context.setParam(TransformContext.PARAM_KEY_CLIENT_ID,
                    IpUtils.X.extractClientIp(context.getHeaders(),
                            context.getParam(TransformContext.PARAM_KEY_REMOTE_HOST_NAME)));
        } finally {
            context.releaseHeaders();
        }
        chain.doFilter(context);
    }
}
