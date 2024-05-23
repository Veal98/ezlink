package cn.itmtx.ddd.ezlink.client.api;

import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import com.alibaba.cola.dto.SingleResponse;

/**
 * accessToken相关接口
 */
public interface TokenService {

    /**
     * 获取 token
     * @param tokenGenerateCmd
     * @return
     */
    SingleResponse<String> generateToken(TokenGenerateCmd tokenGenerateCmd);
}
