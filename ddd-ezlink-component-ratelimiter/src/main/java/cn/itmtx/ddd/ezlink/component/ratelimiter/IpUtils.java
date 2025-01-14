package cn.itmtx.ddd.ezlink.component.ratelimiter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;

/**
 * IP提取工具
 */
public enum IpUtils {

    /**
     * 单例
     */
    X;

    private static final String UNKNOWN_VALUE = "unknown";
    private static final String LOCALHOST_V0 = "0.0.0.0";
    private static final String LOCALHOST_V4 = "127.0.0.1";
    private static final String LOCALHOST_V6 = "0:0:0:0:0:0:0:1";

    private static final String X_FORWARDED_FOR_KEY = "X-Forwarded-For";
    private static final String PROXY_CLIENT_IP_KEY = "Proxy-Client-IP";
    private static final String WL_PROXY_CLIENT_IP_KEY = "WL-Proxy-Client-IP";
    private static final String HTTP_CLIENT_IP_KEY = "HTTP_CLIENT_IP";
    private static final String HTTP_X_FORWARDED_FOR_KEY = "HTTP_X_FORWARDED_FOR";


    /**
     * 通过ServerHttpRequest提取客户端IP
     *
     * @param request request
     * @return String
     */
    public String extractClientIp(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String clientIp = headers.getFirst(X_FORWARDED_FOR_KEY);
        if (StringUtils.isBlank(clientIp) || UNKNOWN_VALUE.equalsIgnoreCase(clientIp)) {
            clientIp = headers.getFirst(PROXY_CLIENT_IP_KEY);
        }
        if (StringUtils.isBlank(clientIp) || UNKNOWN_VALUE.equalsIgnoreCase(clientIp)) {
            clientIp = headers.getFirst(WL_PROXY_CLIENT_IP_KEY);
        }
        if (StringUtils.isBlank(clientIp) || UNKNOWN_VALUE.equalsIgnoreCase(clientIp)) {
            clientIp = headers.getFirst(HTTP_CLIENT_IP_KEY);
        }
        if (StringUtils.isBlank(clientIp) || UNKNOWN_VALUE.equalsIgnoreCase(clientIp)) {
            clientIp = headers.getFirst(HTTP_X_FORWARDED_FOR_KEY);
        }
        if (StringUtils.isBlank(clientIp) || UNKNOWN_VALUE.equalsIgnoreCase(clientIp)) {
            if (Objects.nonNull(request.getRemoteAddress())) {
                clientIp = request.getRemoteAddress().getHostName();
            }
        }
        if (Objects.equals(LOCALHOST_V4, clientIp) || Objects.equals(LOCALHOST_V6, clientIp)) {
            try {
                clientIp = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException unknownhostexception) {
                clientIp = LOCALHOST_V0;
            }
        }
        return clientIp;
    }
}
