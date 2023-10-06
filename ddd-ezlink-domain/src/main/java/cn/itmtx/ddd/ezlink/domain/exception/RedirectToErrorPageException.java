package cn.itmtx.ddd.ezlink.domain.exception;

import com.alibaba.cola.exception.SysException;

/**
 * 需要重定向的异常
 */
public class RedirectToErrorPageException extends SysException {

    private static final String DEFAULT_ERR_CODE = "REDIRECT_ERROR";

    public RedirectToErrorPageException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    public RedirectToErrorPageException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public RedirectToErrorPageException(String errMessage, Throwable e) {
        super(DEFAULT_ERR_CODE, errMessage, e);
    }

    public RedirectToErrorPageException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
