package cn.itmtx.ddd.ezlink.adapter.http;

import cn.itmtx.ddd.ezlink.adapter.constant.API;
import cn.itmtx.ddd.ezlink.client.api.TokenService;
import cn.itmtx.ddd.ezlink.client.dto.command.AppAccessRegisterCmd;
import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.AppAccessDto;
import cn.itmtx.ddd.ezlink.component.ratelimiter.LimitType;
import cn.itmtx.ddd.ezlink.component.ratelimiter.RateLimiter;
import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;


@RestController
@Slf4j
@RequestMapping(API.WEB_API_PATH)
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * 注册 appID
     * @param exchange
     * @return
     */
    @PostMapping("/register")
    @RateLimiter(count = 1, time = 1, limitType = LimitType.IP)
    public SingleResponse<AppAccessDto> register(ServerWebExchange exchange, @RequestBody AppAccessRegisterCmd appAccessRegisterCmd) {
        return tokenService.register(appAccessRegisterCmd);
    }

    /**
     * 生成 access token
     * @param tokenGenerateCmd
     * @return
     */
    @PostMapping("/token")
    @RateLimiter(count = 2, time = 1, limitType = LimitType.IP)
    public SingleResponse<String> getToken(ServerWebExchange exchange, @RequestBody TokenGenerateCmd tokenGenerateCmd) {
        return tokenService.generateToken(tokenGenerateCmd);
    }

}
