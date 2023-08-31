package cn.itmtx.ddd.ezlink.infrastructure.record.assembler;

import cn.itmtx.ddd.ezlink.domain.TransformEventRecordDO;
import cn.itmtx.ddd.ezlink.domain.VisitStatisticsDO;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.TransformEventRecord;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.VisitStatistics;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VisitStatisticsAssembler {

    public VisitStatistics fromVisitStatisticsDO(VisitStatisticsDO visitStatisticsDO) {
        VisitStatistics visitStatistics = new VisitStatistics();
        BeanUtils.copyProperties(visitStatisticsDO, visitStatistics);
        return visitStatistics;
    }

    public VisitStatisticsDO toVisitStatisticsDO(VisitStatistics visitStatistics) {
        VisitStatisticsDO visitStatisticsDO = new VisitStatisticsDO();
        BeanUtils.copyProperties(visitStatistics, visitStatisticsDO);
        return visitStatisticsDO;
    }
}
