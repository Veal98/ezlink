package cn.itmtx.ddd.ezlink.client.dto.command;

import com.alibaba.cola.dto.Command;


public class TokenGenerateCmd extends Command {

    private String appId;

    private String appSecret;

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
}
