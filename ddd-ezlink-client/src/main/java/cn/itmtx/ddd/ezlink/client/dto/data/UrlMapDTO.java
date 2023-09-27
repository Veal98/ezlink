package cn.itmtx.ddd.ezlink.client.dto.data;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

public class UrlMapDTO extends DTO {

    String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
