package cn.itmtx.ddd.ezlink.infrastructure.record.convertor;

import cn.itmtx.ddd.ezlink.domain.domainobject.VisitStatisticsDO;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.VisitStatistics;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VisitStatisticsConvertor {

    public VisitStatistics toPO(VisitStatisticsDO visitStatisticsDO) {
        VisitStatistics visitStatistics = new VisitStatistics();
        BeanUtils.copyProperties(visitStatisticsDO, visitStatistics);
        return visitStatistics;
    }

    public VisitStatisticsDO toDO(VisitStatistics visitStatistics) {
        VisitStatisticsDO visitStatisticsDO = new VisitStatisticsDO();
        BeanUtils.copyProperties(visitStatistics, visitStatisticsDO);
        return visitStatisticsDO;
    }
}
