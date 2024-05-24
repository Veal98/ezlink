package cn.itmtx.ddd.ezlink.domain.domainobject;

import java.util.Date;

public class TokenDO {

    private String appId;

    private String appSecret;

    private String accessToken;

    private Date accessTokenExpireTimestamp;

    private String oldAccessToken;

    private Date oldAccessTokenExpireTimestamp;

    private Integer availableCount;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getAccessTokenExpireTimestamp() {
        return accessTokenExpireTimestamp;
    }

    public void setAccessTokenExpireTimestamp(Date accessTokenExpireTimestamp) {
        this.accessTokenExpireTimestamp = accessTokenExpireTimestamp;
    }

    public String getOldAccessToken() {
        return oldAccessToken;
    }

    public void setOldAccessToken(String oldAccessToken) {
        this.oldAccessToken = oldAccessToken;
    }

    public Date getOldAccessTokenExpireTimestamp() {
        return oldAccessTokenExpireTimestamp;
    }

    public void setOldAccessTokenExpireTimestamp(Date oldAccessTokenExpireTimestamp) {
        this.oldAccessTokenExpireTimestamp = oldAccessTokenExpireTimestamp;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }
}
