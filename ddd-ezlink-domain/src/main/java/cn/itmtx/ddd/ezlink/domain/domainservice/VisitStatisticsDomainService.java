package cn.itmtx.ddd.ezlink.domain.domainservice;

import cn.itmtx.ddd.ezlink.domain.domainobject.VisitStatisticsDO;
import cn.itmtx.ddd.ezlink.domain.gateway.TransformEventRecordGateway;
import cn.itmtx.ddd.ezlink.domain.gateway.VisitStatisticsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class VisitStatisticsDomainService {

    @Autowired
    private VisitStatisticsGateway visitStatisticsGateway;

    @Autowired
    private TransformEventRecordGateway transformEventRecordGateway;

    /**
     * 处理周期性的访客统计
     *
     * @param start start
     * @param end   end
     */
    public void processVisitStatisticsDuration(OffsetDateTime start, OffsetDateTime end) {
        // 统计这个区间内的转换记录
        List<VisitStatisticsDO> visitStatisticsDOs = transformEventRecordGateway.getVisitStatisticsDuration(start, end);

        visitStatisticsDOs.forEach(visitStatisticsDO -> {
            // 查 visit_statistics 表这条统计记录是否存在
            VisitStatisticsDO selectiveVisitStatisticsDO = visitStatisticsGateway.selectByUniqueKey(
                    visitStatisticsDO.getStatisticsDate(),
                    visitStatisticsDO.getCompressionCode(),
                    visitStatisticsDO.getShortUrl(),
                    visitStatisticsDO.getLongUrl());
            if (Objects.isNull(selectiveVisitStatisticsDO)) {
                // 如果不存在则插入
                visitStatisticsGateway.insertVisitStatisticsDO(visitStatisticsDO);
            } else {
                // 存在则更新
                visitStatisticsDO.setId(selectiveVisitStatisticsDO.getId());
                visitStatisticsGateway.updateByPrimaryKeySelective(visitStatisticsDO);
            }
        });
    }

}
