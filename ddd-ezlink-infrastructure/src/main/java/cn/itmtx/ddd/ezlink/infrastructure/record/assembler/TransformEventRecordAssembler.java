package cn.itmtx.ddd.ezlink.infrastructure.record.assembler;

import cn.itmtx.ddd.ezlink.domain.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.domain.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.TransformEventRecord;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.CompressionCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TransformEventRecordAssembler {

    public TransformEventRecord fromTransformEventRecordDO(TransformEventRecordDO transformEventRecordDO) {
        TransformEventRecord transformEventRecord = new TransformEventRecord();
        BeanUtils.copyProperties(transformEventRecordDO, transformEventRecord);
        return transformEventRecord;
    }

    public TransformEventRecordDO toTransformEventDO(TransformEventRecord transformEventRecord) {
        TransformEventRecordDO transformEventRecordDO = new TransformEventRecordDO();
        BeanUtils.copyProperties(transformEventRecord, transformEventRecordDO);
        return transformEventRecordDO;
    }
}
