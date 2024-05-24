package cn.itmtx.ddd.ezlink.application.service;

import cn.itmtx.ddd.ezlink.application.executor.command.AppAccessRegisterCmdExe;
import cn.itmtx.ddd.ezlink.application.executor.command.TokenGenerateCmdExe;
import cn.itmtx.ddd.ezlink.client.api.TokenService;
import cn.itmtx.ddd.ezlink.client.dto.command.AppAccessRegisterCmd;
import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.AppAccessDto;
import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Primary
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenGenerateCmdExe tokenGenerateCmdExe;

    @Autowired
    private AppAccessRegisterCmdExe appAccessRegisterCmdExe;

    @Override
    public SingleResponse<AppAccessDto> register(AppAccessRegisterCmd appAccessRegisterCmd) {
        if (Objects.isNull(appAccessRegisterCmd) || StringUtils.isEmpty(appAccessRegisterCmd.getAppId())) {
            throw new IllegalArgumentException("appId can't be empty.");
        }
        return appAccessRegisterCmdExe.execute(appAccessRegisterCmd.getAppId());
    }

    /**
     * 获取 token
     *
     * @param tokenGenerateCmd
     * @return
     */
    @Override
    public SingleResponse<String> generateToken(TokenGenerateCmd tokenGenerateCmd) {
        if (Objects.isNull(tokenGenerateCmd) || Objects.isNull(tokenGenerateCmd.getAppId()) || StringUtils.isEmpty(tokenGenerateCmd.getAppSecret())) {
            throw new IllegalArgumentException("appId and appSecret can't be empty.");
        }
        return tokenGenerateCmdExe.execute(tokenGenerateCmd);
    }
}
