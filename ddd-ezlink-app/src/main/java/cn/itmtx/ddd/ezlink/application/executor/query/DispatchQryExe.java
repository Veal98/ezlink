package cn.itmtx.ddd.ezlink.application.executor.query;

import cn.itmtx.ddd.ezlink.client.dto.query.DisPatchQry;
import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.domainservice.UrlMapDomain;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.WebFluxServerResponseWriter;
import com.alibaba.cola.dto.Command;
import com.alibaba.cola.dto.Query;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

import static org.springframework.web.cors.CorsConfiguration.ALL;

@Component
public class DispatchQryExe {

    @Value("${ezlink.error.page.url}")
    public String errorPageUrl;

    @Autowired
    private UrlMapDomain urlMapDomain;

    @Autowired
    private WebFluxServerResponseWriter webFluxServerResponseWriter;

    public Mono<Void> execute(DisPatchQry dispatchQry) {
        // 填充 TransformContext
        TransformContext context = generateTransformContext(dispatchQry.getCompressionCode(), dispatchQry.getExchange());
        try {
            // 处理短链转换
            urlMapDomain.processTransform(context);
            // 执行重定向(flush用到的线程和内部逻辑处理的线程不是同一个线程,所有 redirectAction 要用 TTL 存)
            return Mono.fromRunnable(context.getRedirectAction());
        } catch (Exception e) {
            // 短链转换失败直接跳转到 404 界面
            return webFluxServerResponseWriter.redirect(dispatchQry.getExchange(), errorPageUrl);
        }
    }

    private TransformContext generateTransformContext(String compressionCode, ServerWebExchange exchange) {
        TransformContext context = new TransformContext();

        // set params (需要从 header 中提取的信息放到 ExtractRequestHeaderTransformFilter 过滤器中去做)
        ServerHttpRequest request = exchange.getRequest();
        context.setCompressionCode(compressionCode);
        context.setParam(TransformContext.PARAM_KEY_SERVER_WEB_EXCHANGE, exchange);
        if (Objects.nonNull(request.getRemoteAddress())) {
            context.setParam(TransformContext.PARAM_KEY_REMOTE_HOST_NAME, request.getRemoteAddress().getHostName());
        }

        // set headers
        HttpHeaders headers = request.getHeaders();
        Set<String> headerNames = headers.keySet();
        if (!CollectionUtils.isEmpty(headerNames)) {
            headerNames.forEach(headerName -> {
                String headerValue = headers.getFirst(headerName);
                context.setHeader(headerName, headerValue);
            });
        }

        return context;
    }

}
