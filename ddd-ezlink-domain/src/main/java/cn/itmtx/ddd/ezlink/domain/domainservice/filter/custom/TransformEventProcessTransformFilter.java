package cn.itmtx.ddd.ezlink.domain.domainservice.filter.custom;

import cn.itmtx.ddd.ezlink.domain.domainobject.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.domain.domainservice.constant.KafkaConstant;
import cn.itmtx.ddd.ezlink.domain.domainservice.context.TransformContext;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilter;
import cn.itmtx.ddd.ezlink.domain.domainservice.filter.TransformFilterChain;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 记录短链转换(重定向)日志
 */
@Component
@Slf4j
public class TransformEventProcessTransformFilter implements TransformFilter {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public int order() {
        return 4;
    }

    @Override
    public void init(TransformContext context) {
        TransformFilter.super.init(context);
    }

    @Override
    public void doFilter(TransformFilterChain chain, TransformContext context) {
        TransformEventRecordDO transformEventRecordDO = new TransformEventRecordDO();
        transformEventRecordDO.setClientIp(context.getParam(TransformContext.PARAM_KEY_CLIENT_ID));
        transformEventRecordDO.setRecordTime(new Date());
        transformEventRecordDO.setCompressionCode(context.getCompressionCode());
        transformEventRecordDO.setUserAgent(context.getParam(TransformContext.PARAM_KEY_UA));
        transformEventRecordDO.setCookieValue(context.getParam(TransformContext.PARAM_KEY_COOKIE));
        transformEventRecordDO.setShortUrl(context.getParam(TransformContext.PARAM_KEY_SHORT_URL));
        transformEventRecordDO.setLongUrl(context.getParam(TransformContext.PARAM_KEY_LONG_URL));
        transformEventRecordDO.setTransformStatus(context.getTransformStatus());
        String message = new Gson().toJson(transformEventRecordDO);
        kafkaTemplate.send(KafkaConstant.TOPIC_TRANSFORM_EVENT, message);
        if (log.isDebugEnabled()) {
            log.debug("Send kafka message successful, topic:{}, message:{}", KafkaConstant.TOPIC_TRANSFORM_EVENT, message);
        }

        chain.doFilter(context);
    }
}
