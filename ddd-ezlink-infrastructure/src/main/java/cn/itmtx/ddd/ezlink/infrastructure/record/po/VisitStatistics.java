package cn.itmtx.ddd.ezlink.infrastructure.record.po;

import java.util.Date;

public class VisitStatistics {
    private Long id;

    private Date createTime;

    private Date editTime;

    private String creator;

    private String editor;

    private Byte deleted;

    private Long version;

    private Date statisticsDate;

    private Long pvCount;

    private Long uvCount;

    private Long ipCount;

    private Long effectiveRedirectionCount;

    private Long ineffectiveRedirectionCount;

    private String compressionCode;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
}