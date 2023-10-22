package cn.itmtx.ddd.ezlink.domain.domainobject;


import java.util.Date;

public class VisitStatisticsDO {
    private Long id;

    private Date createTime;

    private Date editTime;

    private Date statisticsDate;

    private Long pvCount;

    private Long uvCount;

    private Long ipCount;

    private Long effectiveRedirectionCount;

    private Long ineffectiveRedirectionCount;

    private String compressionCode;

    private String shortUrl;

    private String longUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public Long getPvCount() {
        return pvCount;
    }

    public void setPvCount(Long pvCount) {
        this.pvCount = pvCount;
    }

    public Long getUvCount() {
        return uvCount;
    }

    public void setUvCount(Long uvCount) {
        this.uvCount = uvCount;
    }

    public Long getIpCount() {
        return ipCount;
    }

    public void setIpCount(Long ipCount) {
        this.ipCount = ipCount;
    }

    public Long getEffectiveRedirectionCount() {
        return effectiveRedirectionCount;
    }

    public void setEffectiveRedirectionCount(Long effectiveRedirectionCount) {
        this.effectiveRedirectionCount = effectiveRedirectionCount;
    }

    public Long getIneffectiveRedirectionCount() {
        return ineffectiveRedirectionCount;
    }

    public void setIneffectiveRedirectionCount(Long ineffectiveRedirectionCount) {
        this.ineffectiveRedirectionCount = ineffectiveRedirectionCount;
    }

    public String getCompressionCode() {
        return compressionCode;
    }

    public void setCompressionCode(String compressionCode) {
        this.compressionCode = compressionCode == null ? null : compressionCode.trim();
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl == null ? null : shortUrl.trim();
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl == null ? null : longUrl.trim();
    }
}
