package cn.itmtx.ddd.ezlink.client.dto.data;

import com.alibaba.cola.dto.DTO;

public class UrlMapDTO extends DTO {

    String shortUrl;

    String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
