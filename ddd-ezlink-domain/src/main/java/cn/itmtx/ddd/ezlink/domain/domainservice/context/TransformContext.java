package cn.itmtx.ddd.ezlink.domain.domainservice.context;

import cn.itmtx.ddd.ezlink.domain.domainservice.enums.TransformStatusEnum;
import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TransformContext {

    public static final String PARAM_KEY_CLIENT_ID = "CLIENT_ID";

    public static final String PARAM_KEY_UA = "UA";

    public static final String PARAM_KEY_COOKIE = "COOKIE";

    public static final String PARAM_KEY_SHORT_URL = "SL";

    public static final String PARAM_KEY_LONG_URL = "LL";

    public static final String PARAM_KEY_REMOTE_HOST_NAME = "RHN";

    public static final String PARAM_KEY_SERVER_WEB_EXCHANGE = "SWE";

    /**
     * 存储短链重定向任务
     */
    final ThreadLocal<Runnable> redirectAction = new TransmittableThreadLocal<>();


    /**
     * 压缩码
     */
    private String compressionCode;

    /**
     * 短链转换状态, {@link TransformStatusEnum}
     */
    private Byte transformStatus;

    /**
     * 存储 request header
     */
    private Map<String, String> headers = new HashMap<>();

    /**
     * 存储一些其他需要记录的信息比如 clientID、cookie 等
     */
    private Map<String, Object> params = new HashMap<>();

    public void release() {
        this.headers = null;
        this.params = null;
    }

    public void setParam(String key, Object value) {
        params.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getParam(String key) {
        return (T) params.get(key);
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public void releaseHeaders() {
        headers = null;
    }
    public Runnable getRedirectAction() {
        Runnable redirectAction = this.redirectAction.get();
        return Objects.nonNull(redirectAction) ? redirectAction : () -> {};
    }

    public void setRedirectAction(Runnable redirectAction) {
        this.redirectAction.set(redirectAction);
    }

    public String getCompressionCode() {
        return compressionCode;
    }

    public void setCompressionCode(String compressionCode) {
        this.compressionCode = compressionCode;
    }

    public Byte getTransformStatus() {
        return transformStatus;
    }

    public void setTransformStatus(Byte transformStatus) {
        this.transformStatus = transformStatus;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
