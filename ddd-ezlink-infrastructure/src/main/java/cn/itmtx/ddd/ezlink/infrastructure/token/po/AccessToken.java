package cn.itmtx.ddd.ezlink.infrastructure.token.po;

import java.util.Date;

public class AccessToken {
    private Integer id;

    private String appId;

    private String appSecret;

    private String accessToken;

    private Date accessTokenExpireTimestamp;

    private String oldAccessToken;

    private Date oldAccessTokenExpireTimestamp;

    private Integer availableCount;

    private Date datachangeCreatetime;

    private Date datachangeLasttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
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
        this.oldAccessToken = oldAccessToken == null ? null : oldAccessToken.trim();
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

    public Date getDatachangeCreatetime() {
        return datachangeCreatetime;
    }

    public void setDatachangeCreatetime(Date datachangeCreatetime) {
        this.datachangeCreatetime = datachangeCreatetime;
    }

    public Date getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Date datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }
}