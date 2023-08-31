package cn.itmtx.ddd.ezlink.domain.gateway;

import cn.itmtx.ddd.ezlink.domain.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.domain.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.domain.VisitStatisticsDO;


import java.time.OffsetDateTime;
import java.util.List;

public interface TransformEventRecordGateway {
    void insertTransformEventRecordDO(TransformEventRecordDO transformEventRecordDO);

    List<VisitStatisticsDO> getVisitStatisticsDuration(OffsetDateTime start, OffsetDateTime end);
}
