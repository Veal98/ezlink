package cn.itmtx.ddd.ezlink.application.service;

import cn.itmtx.ddd.ezlink.application.executor.command.TokenGenerateCmdExe;
import cn.itmtx.ddd.ezlink.application.executor.query.TokenQryExe;
import cn.itmtx.ddd.ezlink.client.api.TokenService;
import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import cn.itmtx.ddd.ezlink.client.dto.query.TokenQry;
import com.alibaba.cola.dto.SingleResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author jc.yin
 * @Date 2024/5/23
 * @Description
 **/
@Service
@Primary
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenGenerateCmdExe tokenGenerateCmdExe;

    @Autowired
    private TokenQryExe tokenQryExe;

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

    /**
     * 校验 token
     *
     * @param tokenQry
     * @return
     */
    @Override
    public SingleResponse<Boolean> checkToken(TokenQry tokenQry) {
        // if (Objects.isNull(tokenQry) || StringUtils.isEmpty(tokenQry.getAccessToken()) || StringUtils.isEmpty(tokenQry.getAppSecret()) || Objects.isNull(tokenQry.getAppId())) {
        //     throw new IllegalArgumentException("appId, appSecret and accessToken can't be empty.");
        // }

        return tokenQryExe.execute(tokenQry);
    }
}
