package cn.itmtx.ddd.ezlink.domain.domainservice;

import cn.itmtx.ddd.ezlink.domain.domainobject.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.domain.gateway.TransformEventRecordGateway;
import cn.itmtx.ddd.ezlink.domain.domainservice.util.UserAgentUtils;
import eu.bitwalker.useragentutils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
public class TransformEventRecordDomainService {

    @Autowired
    private TransformEventRecordGateway transformEventRecordGateway;

    public void recordTransformEvent(TransformEventRecordDO transformEventRecordDO) {
        // 身份唯一标识,算法:SHA-1(客户端IP + '-' + UserAgent)
        String uniqueIdentity = DigestUtils.sha1Hex(transformEventRecordDO.getClientIp() + "-" + transformEventRecordDO.getUserAgent());
        transformEventRecordDO.setUniqueIdentity(uniqueIdentity);
        try {
            URL url = new URL(transformEventRecordDO.getShortUrl());
            // 短链的附加参数
            if (StringUtils.isNotEmpty(url.getQuery())) {
                transformEventRecordDO.setQueryParam(url.getQuery());
            }
        } catch (Exception e) {
            log.warn("Parsing the QueryParameter of the short url is abnormal", e);
        }
        // 解析User-Agent
        if (StringUtils.isNotEmpty(transformEventRecordDO.getUserAgent())) {
            try {
                UserAgent userAgent = UserAgent.parseUserAgentString(transformEventRecordDO.getUserAgent());
                OperatingSystem operatingSystem = userAgent.getOperatingSystem();
                // 操作系统
                Optional.ofNullable(operatingSystem).ifPresent(x -> {
                    transformEventRecordDO.setOsType(x.getName());
                    transformEventRecordDO.setOsVersion(x.getName());
                    Optional.ofNullable(x.getDeviceType()).ifPresent(deviceType -> {
                        transformEventRecordDO.setDeviceType(deviceType.getName());
                        // 操作系统组别作为手机类型 - 具体的手机型号
                        if (DeviceType.MOBILE == deviceType) {
                            UserAgentUtils.UserAgentExtractResult result
                                    = UserAgentUtils.X.extractSystemType(transformEventRecordDO.getUserAgent());
                            transformEventRecordDO.setPhoneType(result.getPhoneType());
                            transformEventRecordDO.setOsType(result.getSystemType());
                            transformEventRecordDO.setOsVersion(result.getSystemVersion());
                        }
                    });
                });
                // 浏览器类型
                Browser browser = userAgent.getBrowser();
                Optional.ofNullable(browser).ifPresent(x -> transformEventRecordDO.setBrowserType(x.getGroup().getName()));
                // 浏览器版本
                Version browserVersion = userAgent.getBrowserVersion();
                Optional.ofNullable(browserVersion).ifPresent(x -> transformEventRecordDO.setBrowserVersion(x.getVersion()));
            } catch (Exception e) {
                log.error("Parsing the UserAgent of the short url is abnormal.", e);
            }
        }
        // 插入数据库
        transformEventRecordGateway.insertTransformEventRecordDO(transformEventRecordDO);
    }
}
