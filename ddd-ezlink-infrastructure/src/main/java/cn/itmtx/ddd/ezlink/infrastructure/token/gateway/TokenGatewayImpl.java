package cn.itmtx.ddd.ezlink.infrastructure.token.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.TokenDO;
import cn.itmtx.ddd.ezlink.domain.gateway.TokenGateway;
import cn.itmtx.ddd.ezlink.infrastructure.token.convertor.TokenConvertor;
import cn.itmtx.ddd.ezlink.infrastructure.token.mapper.AccessTokenMapper;
import cn.itmtx.ddd.ezlink.infrastructure.token.po.AccessToken;
import cn.itmtx.ddd.ezlink.infrastructure.token.po.AccessTokenExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenGatewayImpl implements TokenGateway {

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Override
    public void insertToken(TokenDO tokenDO) {
        accessTokenMapper.insertSelective(TokenConvertor.toPO(tokenDO));
    }

    @Override
    public TokenDO selectByAppId(String appId) {
        AccessTokenExample accessTokenExample = new AccessTokenExample();
        accessTokenExample.or().andAppIdEqualTo(appId);
        List<AccessToken> accessTokens = accessTokenMapper.selectByExample(accessTokenExample);
        return CollectionUtils.isEmpty(accessTokens) ? null : TokenConvertor.toDO(accessTokens.get(0));
    }

    @Override
    public TokenDO selectByAppIdAndAppSecret(String appId, String appSecret) {
        AccessTokenExample accessTokenExample = new AccessTokenExample();
        accessTokenExample.or().andAppIdEqualTo(appId).andAppSecretEqualTo(appSecret);
        List<AccessToken> accessTokens = accessTokenMapper.selectByExample(accessTokenExample);
        return CollectionUtils.isEmpty(accessTokens) ? null : TokenConvertor.toDO(accessTokens.get(0));
    }

    @Override
    public void updateByAppIdAndSecret(TokenDO tokenDO) {
        AccessTokenExample accessTokenExample = new AccessTokenExample();
        accessTokenExample.or().andAppIdEqualTo(tokenDO.getAppId()).andAppSecretEqualTo(tokenDO.getAppSecret());
        accessTokenMapper.updateByExampleSelective(TokenConvertor.toPO(tokenDO), accessTokenExample);
    }
}
