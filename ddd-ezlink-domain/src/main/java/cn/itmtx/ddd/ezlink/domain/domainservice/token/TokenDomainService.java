package cn.itmtx.ddd.ezlink.domain.domainservice.token;

import cn.itmtx.ddd.ezlink.domain.domainobject.TokenDO;
import cn.itmtx.ddd.ezlink.domain.gateway.TokenGateway;
import cn.itmtx.ddd.ezlink.domain.utils.DateUtil;
import cn.itmtx.ddd.ezlink.domain.utils.RandomUtil;
import com.alibaba.cola.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class TokenDomainService {

    public static final Integer DEFAULT_AVAILABLE_COUNT = 1000;

    public static final Integer APP_SECRET_LENGTH = 30;

    public static final Long ONE_MINUTE_MILLIS = 60 * 1000L;


    public static final Long ONE_HOUR_MILLIS = 60L * ONE_MINUTE_MILLIS;

    @Autowired
    private TokenGateway tokenGateway;

    /**
     * 注册 appId，生成 appSecret
     *
     * @param appId
     * @return
     */
    public String registerAppId(String appId) {
        TokenDO tokenDO = tokenGateway.selectByAppId(appId);
        if (Objects.nonNull(tokenDO) && StringUtils.isNotEmpty(tokenDO.getAppSecret())) {
            return tokenDO.getAppSecret();
        }

        // 随机生成 appSecret
        String appSecret = RandomUtil.createRandomStr(APP_SECRET_LENGTH);

        TokenDO insertTokenDO = new TokenDO();
        insertTokenDO.setAppId(appId);
        insertTokenDO.setAppSecret(appSecret);
        insertTokenDO.setAvailableCount(DEFAULT_AVAILABLE_COUNT);
        tokenGateway.insertToken(insertTokenDO);

        return appSecret;
    }

    /**
     * 生成 token
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public String generateToken(String appId, String appSecret) {
        // 生成 accessToken
        String accessToken = this.doGenerateToken(appId, appSecret);

        // appId 和 appSecret 要求事先存在
        TokenDO curDbToken = tokenGateway.selectByAppIdAndAppSecret(appId, appSecret);
        if (Objects.isNull(curDbToken)) {
            throw new SysException("appId or appSecret not exists.");
        }

        // 更新
        TokenDO updateTokenDo = new TokenDO();
        updateTokenDo.setAppId(appId);
        updateTokenDo.setAppSecret(appSecret);

        // 更新 old_access_token
        // 将 old_access_token 和 old_access_token_expire_timestamp 的值分别更新为 access_token 和 `min(access_token_expire_timestamp, 当前时间戳) + 5min`（即老 accessToken 5 分钟后过期）
        if (StringUtils.isNotEmpty(curDbToken.getAccessToken())) {
            updateTokenDo.setOldAccessToken(curDbToken.getAccessToken());
            updateTokenDo.setOldAccessTokenExpireTimestamp(DateUtil.longToDate(
                    Math.min(
                            DateUtil.dateToLong(curDbToken.getAccessTokenExpireTimestamp()),
                            System.currentTimeMillis())
                            + 5 * ONE_MINUTE_MILLIS));
        }

        // 更新 access_token
        updateTokenDo.setAccessToken(accessToken);
        updateTokenDo.setAccessTokenExpireTimestamp(DateUtil.longToDate(System.currentTimeMillis() + 2 * ONE_HOUR_MILLIS));
        tokenGateway.updateByAppIdAndSecret(updateTokenDo);

        return accessToken;
    }

    /**
     * 校验 token
     *
     * @param appId
     * @param appSecret
     * @param accessToken
     */
    public Boolean checkToken(String appId, String appSecret, String accessToken) {
        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(appId) || StringUtils.isEmpty(appSecret)) {
            return false;
        }

        TokenDO tokenDO = tokenGateway.selectByAppIdAndAppSecret(appId, appSecret);
        if (Objects.isNull(tokenDO)) {
            return false;
        }

        return doCheckToken(tokenDO, accessToken);
    }

    private Boolean doCheckToken(TokenDO dbToken, String targetAccessToken) {
        String dbAccessToken = dbToken.getAccessToken();
        if (!dbAccessToken.equalsIgnoreCase(targetAccessToken)) {
            return false;
        }
        // 检查过期时间和调用次数
        if (DateUtil.dateToLong(dbToken.getAccessTokenExpireTimestamp()) >= System.currentTimeMillis() &&
                dbToken.getAvailableCount() > 0) {
            return true;
        }

        // 否则继续检查 oldToken
        String oldDbAccessToken = dbToken.getOldAccessToken();
        if (StringUtils.isNotEmpty(oldDbAccessToken)) {
            if (!oldDbAccessToken.equalsIgnoreCase(targetAccessToken)) {
                return false;
            }

            // 检查过期时间和调用次数
            return DateUtil.dateToLong(dbToken.getOldAccessTokenExpireTimestamp()) >= System.currentTimeMillis() &&
                    dbToken.getAvailableCount() > 0;
        }

        return false;
    }

    /**
     * TODO 随机生成 Token
     *
     * @param appId
     * @param appSecret
     * @return
     */
    private String doGenerateToken(String appId, String appSecret) {
        return UUID.randomUUID().toString();
    }
}
