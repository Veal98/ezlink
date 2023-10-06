package cn.itmtx.ddd.ezlink.client.dto.query;

import com.alibaba.cola.dto.Query;

public class LongByShortQry extends Query {

    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
