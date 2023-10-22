package cn.itmtx.ddd.ezlink.adapter.mq;

import cn.itmtx.ddd.ezlink.domain.domainobject.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.domain.domainservice.constant.KafkaConstant;
import cn.itmtx.ddd.ezlink.domain.domainservice.TransformEventRecordDomainService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransformEventConsumer {

    @Autowired
    private TransformEventRecordDomainService transformEventRecordDomainService;

    @KafkaListener(topics = {KafkaConstant.TOPIC_TRANSFORM_EVENT})
    public void onTransformEvent(String message) {
        if (log.isDebugEnabled()) {
            log.info("Data received from {}, message:{}", KafkaConstant.TOPIC_TRANSFORM_EVENT, message);
        }

        TransformEventRecordDO transformEventRecordDO = new Gson().fromJson(message, TransformEventRecordDO.class);

        // 插入数据库
        transformEventRecordDomainService.recordTransformEvent(transformEventRecordDO);
    }
}
