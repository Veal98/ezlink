package cn.itmtx.ddd.ezlink.adapter.http.advice;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HTTP 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class HttpGlobalExceptionAdvice {

    @ExceptionHandler(value= SysException.class)
    @ResponseBody
    public Response sysExceptionHandler(SysException e) {
        log.error("SYS EXCEPTION: {}", e.getMessage(), e);
        return Response.buildFailure(e.getErrCode(), e.getMessage());
    }

    @ExceptionHandler(value= BizException.class)
    @ResponseBody
    public Response bizExceptionHandler(BizException e) {
        log.error("BIZ EXCEPTION: {}", e.getMessage(), e);
        return Response.buildFailure(e.getErrCode(), e.getMessage());
    }

    @ExceptionHandler(value= Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e) {
        log.error("UNKNOWN EXCEPTION: {}", e.getMessage(), e);
        return Response.buildFailure("UNKNOWN_EXCEPTION", e.getMessage());
    }

}
