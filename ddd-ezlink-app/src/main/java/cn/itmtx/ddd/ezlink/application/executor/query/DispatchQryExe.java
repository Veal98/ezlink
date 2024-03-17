package cn.itmtx.ddd.ezlink.application.executor.query;

import cn.itmtx.ddd.ezlink.client.dto.query.DispatchQry;
import cn.itmtx.ddd.ezlink.domain.domainservice.UrlMapDomainService;
import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.WebFluxServerResponseWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;
import java.util.Set;

@Component
@Slf4j
public class DispatchQryExe {

    @Autowired
    private UrlMapDomainService urlMapDomainService;

    @Autowired
    private WebFluxServerResponseWriter webFluxServerResponseWriter;

    public Mono<Void> execute(DispatchQry dispatchQry) {
        // 填充 TransformContext
        TransformContext context = generateTransformContext(dispatchQry.getCompressionCode(), dispatchQry.getExchange());

        // 处理短链转换
        urlMapDomainService.processTransform(context);
        // 执行重定向(flush用到的线程和内部逻辑处理的线程不是同一个线程, 所以 redirectAction 要用 TTL 存)
        log.info("构造重定向任务的线程 ID:" + Thread.currentThread().getId());
        return Mono.fromRunnable(context.getRedirectAction())
                .publishOn(Schedulers.parallel())
                .doOnSuccess(value -> log.info("真正执行重定向任务的线程 ID:" + Thread.currentThread().getId())).then();
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
