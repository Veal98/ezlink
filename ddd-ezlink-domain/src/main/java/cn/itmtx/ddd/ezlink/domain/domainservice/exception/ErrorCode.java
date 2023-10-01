package cn.itmtx.ddd.ezlink.domain.domainservice.exception;

public enum ErrorCode {

    REDIRECT_TO_ERROR_PAGE_EXCEPTION("RedirectToErrorPageException", "压缩码不存在或过期，重定向到 404 页面");

    private String defaultErrCode;
    private String defaultErrMessage;

    ErrorCode(String defaultErrCode, String defaultErrMessage) {
        this.defaultErrCode = defaultErrCode;
        this.defaultErrMessage = defaultErrMessage;
    }

    public String getDefaultErrCode() {
        return defaultErrCode;
    }

    public String getDefaultErrMessage() {
        return defaultErrMessage;
    }
}
