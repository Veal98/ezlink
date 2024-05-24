package cn.itmtx.ddd.ezlink.domain.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.TokenDO;

public interface TokenGateway {

    /**
     * 插入 token 记录
     * @param tokenDO
     */
    void insertToken(TokenDO tokenDO);

    /**
     * 根据 appId 查询
     * @param appId
     * @return
     */
    TokenDO selectByAppId(String appId);

    /**
     * 根据 appId 和 appSecret 查询
     * @param appId
     * @param appSecret
     * @return
     */
    TokenDO selectByAppIdAndAppSecret(String appId, String appSecret);

    /**
     * 根据 appId 和 appSecret 更新
     * @param tokenDO
     */
    void updateByAppIdAndSecret(TokenDO tokenDO);
}
