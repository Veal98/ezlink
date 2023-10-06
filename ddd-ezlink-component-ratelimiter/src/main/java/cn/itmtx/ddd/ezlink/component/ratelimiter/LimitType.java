package cn.itmtx.ddd.ezlink.component.ratelimiter;

public enum LimitType {

    /**
     * 默认策略：全局限流
     */
    DEFAULT,

    /**
     * 指定客户端 IP 限流
     */
    IP
}
