package cn.itmtx.ddd.ezlink.client.dto.command;

import cn.itmtx.ddd.ezlink.client.dto.query.TokenQry;
import com.alibaba.cola.dto.Command;

public class UrlMapAddCmd extends Command {

    private String longUrl;
    private String description;

    private TokenQry access;

    public TokenQry getAccess() {
        return access;
    }

    public void setAccess(TokenQry access) {
        this.access = access;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
