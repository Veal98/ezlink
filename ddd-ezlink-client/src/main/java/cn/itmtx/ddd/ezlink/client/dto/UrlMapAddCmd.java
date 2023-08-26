package cn.itmtx.ddd.ezlink.client.dto;

public class UrlMapAddCmd {

    private String longUrl;
    private String description;

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
