package cn.itmtx.ddd.ezlink.application.executor.command;

import cn.itmtx.ddd.ezlink.client.dto.data.AppAccessDto;
import cn.itmtx.ddd.ezlink.domain.domainservice.token.TokenDomainService;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppAccessRegisterCmdExe {

    @Autowired
    private TokenDomainService tokenDomainService;

    public SingleResponse<AppAccessDto> execute(String appId) {
        String appSecret = tokenDomainService.registerAppId(appId);

        AppAccessDto appAccessDto = new AppAccessDto();
        appAccessDto.setAppId(appId);
        appAccessDto.setAppSecret(appSecret);
        return SingleResponse.of(appAccessDto);
    }
}
