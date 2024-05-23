package cn.itmtx.ddd.ezlink.client.dto.query;

import com.alibaba.cola.dto.Query;

/**
 * @Author jc.yin
 * @Date 2024/5/23
 * @Description
 **/
public class TokenQry extends Query {

    private Long appId;

    private String appSecret;

    private String accessToken;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
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
}
