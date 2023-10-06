package cn.itmtx.ddd.ezlink.component.ratelimiter;

import com.alibaba.cola.exception.SysException;

/**
 * 自定义限流异常
 */
public class RateLimitException extends SysException {
    private static final String DEFAULT_ERR_CODE = "RATE_LIMIT_ERROR";

    public RateLimitException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    public RateLimitException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public RateLimitException(String errMessage, Throwable e) {
        super(DEFAULT_ERR_CODE, errMessage, e);
    }

    public RateLimitException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
