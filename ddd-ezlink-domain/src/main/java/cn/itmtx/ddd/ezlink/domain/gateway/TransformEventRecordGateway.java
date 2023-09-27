package cn.itmtx.ddd.ezlink.domain.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.domain.domainobject.VisitStatisticsDO;


import java.time.OffsetDateTime;
import java.util.List;

public interface TransformEventRecordGateway {
    void insertTransformEventRecordDO(TransformEventRecordDO transformEventRecordDO);

    List<VisitStatisticsDO> getVisitStatisticsDuration(OffsetDateTime start, OffsetDateTime end);
}
