package cn.itmtx.ddd.ezlink.domain.domainservice.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.cors.CorsConfiguration.ALL;

@Slf4j
public class WebFluxServerResponseWriter {

    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();

    private List<ViewResolver> viewResolvers = Collections.emptyList();

    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        Assert.notNull(messageReaders, "'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }

    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters, "'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }

    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }


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

    public Mono<Void> write(ServerWebExchange exchange, String content, Throwable throwable) {
        Mono<ServerResponse> serverResponseMono = buildApplicationJsonBodyServerResponse(content);
        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(), x -> serverResponseMono).route(newRequest)
                .switchIfEmpty(Mono.error(throwable))
                .flatMap(handler -> handler.handle(newRequest))
                .flatMap(resp -> writeInternal(exchange, resp));
    }

    private Mono<Void> writeInternal(ServerWebExchange exchange, ServerResponse response) {
        exchange.getResponse().getHeaders().setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    private Mono<ServerResponse> buildApplicationJsonBodyServerResponse(String bodyContent) {
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALL)
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALL)
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString())
                .body(BodyInserters.fromValue(bodyContent));
    }

    private class ResponseContext implements ServerResponse.Context {
        private ResponseContext() {
        }

        @NonNull
        public List<HttpMessageWriter<?>> messageWriters() {
            return messageWriters;
        }

        @NonNull
        public List<ViewResolver> viewResolvers() {
            return viewResolvers;
        }
    }
}
