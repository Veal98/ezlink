package cn.itmtx.ddd.ezlink.domain.exception;

import com.alibaba.cola.exception.SysException;

public class TokenUnValidException extends SysException {

    private static final String DEFAULT_ERR_CODE = "ACCESS_TOKEN_UN_VALID";

    private static final String DEFAULT_ERR_MSG = "access token not valid.";


    public TokenUnValidException() {
        super(DEFAULT_ERR_CODE, DEFAULT_ERR_MSG);
    }

    public TokenUnValidException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    public TokenUnValidException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public TokenUnValidException(String errMessage, Throwable e) {
        super(DEFAULT_ERR_CODE, errMessage, e);
    }

    public TokenUnValidException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
