package cn.itmtx.ddd.ezlink.client.api;

import cn.itmtx.ddd.ezlink.client.dto.command.AppAccessRegisterCmd;
import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.AppAccessDto;
import com.alibaba.cola.dto.SingleResponse;

/**
 * accessToken相关接口
 */
public interface TokenService {

    SingleResponse<AppAccessDto> register(AppAccessRegisterCmd appAccessRegisterCmd);

    /**
     * 获取 token
     * @param tokenGenerateCmd
     * @return
     */
    SingleResponse<String> generateToken(TokenGenerateCmd tokenGenerateCmd);
}
