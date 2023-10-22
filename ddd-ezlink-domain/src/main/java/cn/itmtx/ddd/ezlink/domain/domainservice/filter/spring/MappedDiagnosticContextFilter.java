package cn.itmtx.ddd.ezlink.domain.domainservice.filter.spring;

import com.alibaba.cola.exception.SysException;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

/**
 * 在每次请求开始之前设置 TRACE_ID, 请求结束后清除 TRACE_ID
 */
@Order(value = Integer.MIN_VALUE)
@Component
@Deprecated
public class MappedDiagnosticContextFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (Objects.isNull(exchange) || Objects.isNull(chain)) {
            throw new SysException("ServerWebExchange or WebFilterChain can not be null");
        }

        String uuid = UUID.randomUUID().toString();
        MDC.put("TRACE_ID", uuid);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> MDC.remove("TRACE_ID")));
    }
}
