package cn.itmtx.ddd.ezlink.adapter.http.filter;

import cn.itmtx.ddd.ezlink.domain.domainservice.token.TokenCheck;
import cn.itmtx.ddd.ezlink.domain.domainservice.token.TokenDomainService;
import cn.itmtx.ddd.ezlink.domain.exception.TokenUnValidException;
import com.alibaba.cola.dto.Response;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Configuration
public class TokenFilter implements WebFilter, Ordered {

    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    public static final String APP_ID = "APP_ID";

    public static final String APP_SECRET = "APP_SECRET";

    /**
     * 是否放行
     */
    private Boolean access;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private TokenDomainService tokenDomainService;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 获取Response、Request
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        // 获取请求对应的HandlerMethod
        Mono<HandlerMethod> handlerMethodMono = requestMappingHandlerMapping.getHandler(exchange).cast(HandlerMethod.class);

        handlerMethodMono.subscribe(handlerMethod -> {
            // 判断方法头是否有注解
            boolean flag = handlerMethod.hasMethodAnnotation(TokenCheck.class);
            if (!flag) {
                access = true;
                return ;
            }

            HttpHeaders headers = serverHttpRequest.getHeaders();
            String accessToken = headers.getFirst(ACCESS_TOKEN);
            String appId = headers.getFirst(APP_ID);
            String appSecret = headers.getFirst(APP_SECRET);

            // 校验 token
            access = tokenDomainService.checkToken(appId, appSecret, accessToken);
        }).dispose();

        if (access) {
            return chain.filter(exchange);
        }
        TokenUnValidException tokenUnValidException = new TokenUnValidException();
        Response response = Response.buildFailure(tokenUnValidException.getErrCode(), tokenUnValidException.getMessage());
        serverHttpResponse.setStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        return serverHttpResponse.writeWith(Mono.just(getResponseBuffer(response, serverHttpResponse)));
    }


    public DataBuffer getResponseBuffer(Response response, ServerHttpResponse serverHttpResponse){
        // JSON转换
        byte[] bytes = new Gson().toJson(response).getBytes(StandardCharsets.UTF_8);

        // 调用bufferFactory方法,生成DataBuffer对象
        return serverHttpResponse.bufferFactory().wrap(bytes);
    }

}
