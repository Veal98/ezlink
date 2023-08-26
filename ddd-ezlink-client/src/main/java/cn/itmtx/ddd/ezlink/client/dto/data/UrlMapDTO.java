package cn.itmtx.ddd.ezlink.client.dto.data;

import lombok.Data;

public class UrlMapDTO {

    String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
