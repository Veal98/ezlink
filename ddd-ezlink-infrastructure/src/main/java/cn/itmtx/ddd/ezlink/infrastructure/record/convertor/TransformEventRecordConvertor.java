package cn.itmtx.ddd.ezlink.infrastructure.record.convertor;

import cn.itmtx.ddd.ezlink.domain.domainobject.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.TransformEventRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TransformEventRecordConvertor {

    public TransformEventRecord toPO(TransformEventRecordDO transformEventRecordDO) {
        TransformEventRecord transformEventRecord = new TransformEventRecord();
        BeanUtils.copyProperties(transformEventRecordDO, transformEventRecord);
        return transformEventRecord;
    }

    public TransformEventRecordDO toDO(TransformEventRecord transformEventRecord) {
        TransformEventRecordDO transformEventRecordDO = new TransformEventRecordDO();
        BeanUtils.copyProperties(transformEventRecord, transformEventRecordDO);
        return transformEventRecordDO;
    }
}
