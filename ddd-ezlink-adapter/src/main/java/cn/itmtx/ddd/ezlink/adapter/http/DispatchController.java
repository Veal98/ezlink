package cn.itmtx.ddd.ezlink.adapter.http;

import cn.itmtx.ddd.ezlink.client.api.DispatchService;
import cn.itmtx.ddd.ezlink.client.dto.query.DispatchQry;
import cn.itmtx.ddd.ezlink.component.ratelimiter.LimitType;
import cn.itmtx.ddd.ezlink.component.ratelimiter.RateLimitException;
import cn.itmtx.ddd.ezlink.component.ratelimiter.RateLimiter;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.WebFluxServerResponseWriter;
import cn.itmtx.ddd.ezlink.domain.exception.RedirectToErrorPageException;
import com.alibaba.cola.dto.Response;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class DispatchController {

    @Value("${ezlink.error.page.url}")
    public String errorPageUrl;

    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private WebFluxServerResponseWriter webFluxServerResponseWriter;

    /**
     * 长链重定向
     *
     * @param compressionCode
     * @param exchange
     * @return
     */
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{compressionCode}")
    // @RateLimiter(count = 3, time = 5, limitType = LimitType.IP)
    public Mono<Void> dispatch(ServerWebExchange exchange, @PathVariable(name = "compressionCode") String compressionCode) {
        DispatchQry dispatchQry = new DispatchQry();
        dispatchQry.setCompressionCode(compressionCode);
        dispatchQry.setExchange(exchange);
        return dispatchService.dispatch(dispatchQry);
    }

    @ExceptionHandler(RateLimitException.class)
    public Mono<Void> rateLimitExceptionHandler(ServerWebExchange serverWebExchange, RateLimitException rateLimitException) {
        // 输出 JSON 字符串内容
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(rateLimitException.getErrCode());
        response.setErrMessage(rateLimitException.getMessage());
        return webFluxServerResponseWriter.write(
                serverWebExchange,
                new Gson().toJson(response),
                rateLimitException
        );
    }

    @ExceptionHandler(RedirectToErrorPageException.class)
    public Mono<Void> redirectToErrorPageExceptionHandler(ServerWebExchange serverWebExchange, RedirectToErrorPageException redirectToErrorPageException) {
        // 短链转换失败跳转到 404 界面
        return webFluxServerResponseWriter.redirect(serverWebExchange, errorPageUrl);
    }

    @ExceptionHandler(Exception.class)
    public Mono<Void> exceptionHandler(ServerWebExchange serverWebExchange, Exception exception) {
        log.error(exception.getMessage());
        // 输出 JSON 字符串内容
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setErrMessage("服务器未知异常，请稍后再试!");
        return webFluxServerResponseWriter.write(
                serverWebExchange,
                new Gson().toJson(response),
                exception
        );
    }
}
