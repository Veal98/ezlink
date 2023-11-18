package cn.itmtx.ddd.ezlink.client.dto.query;

import com.alibaba.cola.dto.Query;

public class LongByShortQry extends Query {

    private String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
