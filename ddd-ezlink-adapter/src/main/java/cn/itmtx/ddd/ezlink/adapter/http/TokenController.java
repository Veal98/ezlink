package cn.itmtx.ddd.ezlink.adapter.http;

import cn.itmtx.ddd.ezlink.adapter.constant.API;
import cn.itmtx.ddd.ezlink.client.api.TokenService;
import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import cn.itmtx.ddd.ezlink.component.ratelimiter.LimitType;
import cn.itmtx.ddd.ezlink.component.ratelimiter.RateLimiter;
import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping(API.WEB_API_PATH)
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * 生成 access token
     * @param tokenGenerateCmd
     * @return
     */
    @PostMapping("/token")
    @RateLimiter(count = 3, time = 1, limitType = LimitType.IP)
    public SingleResponse<String> getToken(@RequestBody TokenGenerateCmd tokenGenerateCmd) {
        return tokenService.generateToken(tokenGenerateCmd);
    }

}
