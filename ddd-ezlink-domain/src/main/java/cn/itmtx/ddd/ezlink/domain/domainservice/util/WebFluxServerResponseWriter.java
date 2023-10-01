package cn.itmtx.ddd.ezlink.domain.domainservice.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.net.URI;
import static org.springframework.web.cors.CorsConfiguration.ALL;

@Component
public class WebFluxServerResponseWriter {

    /**
     * 注意：直接在 doFilter 中写 runnable lambda 表达式可能会导致上下文切换
     * 将 lambda 表达式封装成方法，实际上是将整个异步操作封装在同一个方法内部
     * 这有助于确保在整个方法执行期间，异步操作都在同一上下文中执行
     * 封装成方法会创建一个边界，使得异步操作不会跨越不同的操作链和线程切换
     * 封装成方法的作用类似于创建一个同步的执行单元，而不需要关心异步操作中的线程切换和上下文传递
     * @param exchange
     * @param url 要跳转的 url
     * @return
     */
    public Runnable redirectAction(ServerWebExchange exchange, String url) {
        return () -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FOUND);
            response.getHeaders().setLocation(URI.create(url));
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
        };
    }

    public Mono<Void> redirect(ServerWebExchange exchange, String url) {
        ServerHttpResponse response = exchange.getResponse();
        if (!response.isCommitted()) {
            response.setStatusCode(HttpStatus.FOUND);
            response.getHeaders().setLocation(URI.create(url));
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
            return response.setComplete();
        }
        return Mono.empty();
    }
}
