package cn.itmtx.ddd.ezlink.client.dto.command;

import com.alibaba.cola.dto.Command;

/**
 * @Author jc.yin
 * @Date 2024/5/23
 * @Description
 **/
public class TokenGenerateCmd extends Command {

    private Long appId;

    private String appSecret;

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
}
