package cn.itmtx.ddd.ezlink.infrastructure.record;

import cn.itmtx.ddd.ezlink.domain.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.domain.gateway.TransformEventRecordGateway;
import cn.itmtx.ddd.ezlink.infrastructure.record.assembler.TransformEventRecordAssembler;
import cn.itmtx.ddd.ezlink.infrastructure.record.mapper.TransformEventRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransformEventRecordGatewayImpl implements TransformEventRecordGateway {

    @Autowired
    private TransformEventRecordMapper transformEventRecordMapper;

    @Autowired
    private TransformEventRecordAssembler transformEventRecordAssembler;

    @Override
    public void insertTransformEventRecordDO(TransformEventRecordDO transformEventRecordDO) {
        transformEventRecordMapper.insertSelective(transformEventRecordAssembler.fromTransformEventRecordDO(transformEventRecordDO));
    }
}
