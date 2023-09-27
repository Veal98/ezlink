package cn.itmtx.ddd.ezlink.infrastructure.record.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.domain.domainobject.VisitStatisticsDO;
import cn.itmtx.ddd.ezlink.domain.gateway.TransformEventRecordGateway;
import cn.itmtx.ddd.ezlink.infrastructure.record.convertor.TransformEventRecordConvertor;
import cn.itmtx.ddd.ezlink.infrastructure.record.mapper.TransformEventRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class TransformEventRecordGatewayImpl implements TransformEventRecordGateway {

    @Autowired
    private TransformEventRecordMapper transformEventRecordMapper;

    @Autowired
    private TransformEventRecordConvertor transformEventRecordConvertor;

    @Override
    public void insertTransformEventRecordDO(TransformEventRecordDO transformEventRecordDO) {
        transformEventRecordMapper.insertSelective(transformEventRecordConvertor.toPO(transformEventRecordDO));
    }

    @Override
    public List<VisitStatisticsDO> getVisitStatisticsDuration(OffsetDateTime start, OffsetDateTime end) {
        return transformEventRecordMapper.selectVisitStatisticsDuration(start, end);
    }
}
