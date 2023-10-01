package cn.itmtx.ddd.ezlink.domain.domainservice.exception;

import com.alibaba.cola.exception.SysException;

/**
 * 需要重定向的异常
 */
public class RedirectToErrorPageException extends SysException {

    public RedirectToErrorPageException(String errMessage) {
        super(ErrorCode.REDIRECT_TO_ERROR_PAGE_EXCEPTION.getDefaultErrCode(), errMessage);
    }

    public RedirectToErrorPageException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public RedirectToErrorPageException(String errMessage, Throwable e) {
        super(ErrorCode.REDIRECT_TO_ERROR_PAGE_EXCEPTION.getDefaultErrCode(), errMessage, e);
    }

    public RedirectToErrorPageException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
