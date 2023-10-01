package cn.itmtx.ddd.ezlink.domain.domainservice.enums;

public enum LockKeyEnum {
    /**
     * 创建短链映射
     */
    CREATE_URL_MAP("ezlink:url:map:create", "创建URL映射", 0L, 10000L),

    /**
     * 编辑短链映射
     */
    EDIT_URL_MAP("ezlink:url:map:edit", "修改URL映射", 0L, 10000L),

    /**
     * 访问统计任务
     */
    VISITOR_STATS_TASK("ezlink:visitor:stats:task", "访问统计任务", 0L, 10000L);

    private final String code;
    private final String desc;
    private final long waitTime;
    private final long releaseTime;

    LockKeyEnum(String code, String desc, long waitTime, long releaseTime) {
        this.code = code;
        this.desc = desc;
        this.waitTime = waitTime;
        this.releaseTime = releaseTime;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public long getReleaseTime() {
        return releaseTime;
    }
}
