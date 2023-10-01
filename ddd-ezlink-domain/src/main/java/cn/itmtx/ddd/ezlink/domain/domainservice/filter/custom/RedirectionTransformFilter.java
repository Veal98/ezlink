package cn.itmtx.ddd.ezlink.domain.domainservice.filter.custom;

import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.TransformStatusEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilter;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilterChain;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.WebFluxServerResponseWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * 重定向过滤器，核心工作是 context.setRedirectAction()
 */
@Component
@Slf4j
public class RedirectionTransformFilter implements TransformFilter {

    @Autowired
    private WebFluxServerResponseWriter webFluxServerResponseWriter;

    @Override
    public int order() {
        return 3;
    }

    @Override
    public void init(TransformContext context) {
        TransformFilter.super.init(context);
    }

    @Override
    public void doFilter(TransformFilterChain chain, TransformContext context) {
        // 转换成功，进行重定向
        if (TransformStatusEnum.TRANSFORM_SUCCESS.getValue().equals(context.getTransformStatus())) {
            String longUrl = context.getParam(TransformContext.PARAM_KEY_LONG_URL);
            try {
                if (StringUtils.isNotEmpty(longUrl)) {
                    Runnable redirection = webFluxServerResponseWriter.redirectAction(context.getParam(TransformContext.PARAM_KEY_SERVER_WEB_EXCHANGE), longUrl);
                    context.setRedirectAction(redirection);
                    // 更新状态为重定向成功
                    context.setTransformStatus(TransformStatusEnum.REDIRECTION_SUCCESS.getValue());
                } else {
                    context.setTransformStatus(TransformStatusEnum.REDIRECTION_FAIL.getValue());
                    log.warn("Redirection to long url failed, long url is empty, compressionCode:{}", context.getCompressionCode());
                }
            } catch (Exception e) {
                log.error("重定向到长链接 [{}] 失败,压缩码:{}", longUrl, context.getCompressionCode(), e);
                context.setTransformStatus(TransformStatusEnum.REDIRECTION_FAIL.getValue());
            }
        }

        chain.doFilter(context);
    }

}
