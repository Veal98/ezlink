package cn.itmtx.ddd.ezlink.domain.domainservice.filter.custom;

import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.TransformStatusEnum;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilter;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilterChain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        if (TransformStatusEnum.TRANSFORM_SUCCESS.getValue().equals(context.getTransformStatus())) {
            String longUrl = context.getParam(TransformContext.PARAM_KEY_LONG_URL);
            if (StringUtils.isNotEmpty(longUrl)) {
                Runnable redirection = redirectAction(context.getParam(TransformContext.PARAM_KEY_SERVER_WEB_EXCHANGE), longUrl);
                context.setRedirectAction(redirection);
                // 更新状态为重定向成功
                context.setTransformStatus(TransformStatusEnum.REDIRECTION_SUCCESS.getValue());
            } else {
                context.setTransformStatus(TransformStatusEnum.REDIRECTION_FAIL.getValue());
                log.warn("Redirection to long url failed, long url is empty, compressionCode:{}", context.getCompressionCode());
            }
        }

        chain.doFilter(context);
    }

    /**
     * 注意：直接在 doFilter 中写 runnable lambda 表达式可能会导致上下文切换
     * 将 lambda 表达式封装成方法，实际上是将整个异步操作封装在同一个方法内部
     * 这有助于确保在整个方法执行期间，异步操作都在同一上下文中执行
     * 封装成方法会创建一个边界，使得异步操作不会跨越不同的操作链和线程切换
     * 封装成方法的作用类似于创建一个同步的执行单元，而不需要关心异步操作中的线程切换和上下文传递
     * @param exchange
     * @param url
     * @return
     */
    private Runnable redirectAction(ServerWebExchange exchange, String url) {
        return () -> {
            ServerHttpResponse response = exchange.getResponse();
            // 设置 302 临时重定向状态码
            response.setStatusCode(HttpStatus.FOUND);
            response.getHeaders().setLocation(URI.create(url));
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
        };
    }
}
