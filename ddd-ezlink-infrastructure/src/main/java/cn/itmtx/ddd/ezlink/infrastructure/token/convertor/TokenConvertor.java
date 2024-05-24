package cn.itmtx.ddd.ezlink.infrastructure.token.convertor;

import cn.itmtx.ddd.ezlink.domain.domainobject.TokenDO;
import cn.itmtx.ddd.ezlink.infrastructure.token.po.AccessToken;

import java.util.Objects;

public class TokenConvertor {
    public static AccessToken toPO(TokenDO tokenDO) {
        if (Objects.isNull(tokenDO)) {
            return null;
        }

        AccessToken accessToken = new AccessToken();
        accessToken.setAppId(tokenDO.getAppId());
        accessToken.setAppSecret(tokenDO.getAppSecret());
        accessToken.setAccessToken(tokenDO.getAccessToken());
        accessToken.setAccessTokenExpireTimestamp(tokenDO.getAccessTokenExpireTimestamp());
        accessToken.setOldAccessToken(tokenDO.getOldAccessToken());
        accessToken.setOldAccessTokenExpireTimestamp(tokenDO.getOldAccessTokenExpireTimestamp());
        accessToken.setAvailableCount(tokenDO.getAvailableCount());
        return accessToken;
    }

    public static TokenDO toDO(AccessToken accessToken) {
        if (Objects.isNull(accessToken)) {
            return null;
        }

        TokenDO tokenDO = new TokenDO();
        tokenDO.setAppId(accessToken.getAppId());
        tokenDO.setAppSecret(accessToken.getAppSecret());
        tokenDO.setAccessToken(accessToken.getAccessToken());
        tokenDO.setAccessTokenExpireTimestamp(accessToken.getAccessTokenExpireTimestamp());
        tokenDO.setOldAccessToken(accessToken.getOldAccessToken());
        tokenDO.setOldAccessTokenExpireTimestamp(accessToken.getOldAccessTokenExpireTimestamp());
        tokenDO.setAvailableCount(accessToken.getAvailableCount());
        return tokenDO;


    }
}
