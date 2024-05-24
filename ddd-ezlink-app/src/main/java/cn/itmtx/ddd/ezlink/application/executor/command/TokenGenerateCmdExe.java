package cn.itmtx.ddd.ezlink.application.executor.command;

import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import cn.itmtx.ddd.ezlink.domain.domainservice.token.TokenDomainService;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerateCmdExe {

    @Autowired
    private TokenDomainService tokenDomainService;

    public SingleResponse<String> execute(TokenGenerateCmd tokenGenerateCmd) {
        String accessToken = tokenDomainService.generateToken(tokenGenerateCmd.getAppId(), tokenGenerateCmd.getAppSecret());
        return SingleResponse.of(accessToken);
    }
}
