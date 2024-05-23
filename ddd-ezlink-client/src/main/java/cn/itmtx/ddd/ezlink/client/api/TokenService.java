package cn.itmtx.ddd.ezlink.client.api;

import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import cn.itmtx.ddd.ezlink.client.dto.query.TokenQry;
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

    /**
     * 校验 token
     * @param tokenQry
     * @return
     */
    SingleResponse<Boolean> checkToken(TokenQry tokenQry);

}
