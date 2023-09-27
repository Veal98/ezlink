package cn.itmtx.ddd.ezlink.infrastructure.record.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.VisitStatisticsDO;
import cn.itmtx.ddd.ezlink.domain.gateway.VisitStatisticsGateway;
import cn.itmtx.ddd.ezlink.infrastructure.record.convertor.VisitStatisticsConvertor;
import cn.itmtx.ddd.ezlink.infrastructure.record.mapper.VisitStatisticsMapper;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.VisitStatistics;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.VisitStatisticsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Repository
public class VisitStatisticsGatewayImpl implements VisitStatisticsGateway {

    @Autowired
    private VisitStatisticsMapper visitStatisticsMapper;

    @Autowired
    private VisitStatisticsConvertor visitStatisticsConvertor;

    @Override
    public VisitStatisticsDO selectByUniqueKey(Date statisticsDate, String compressionCode, String shortUrl, String longUrl) {
        VisitStatisticsExample example = new VisitStatisticsExample();
        example.or()
                .andStatisticsDateEqualTo(statisticsDate)
                .andCompressionCodeEqualTo(compressionCode)
                .andShortUrlEqualTo(shortUrl)
                .andLongUrlEqualTo(longUrl);

        List<VisitStatistics> visitStatistics = visitStatisticsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(visitStatistics)) {
            return null;
        }

        return visitStatisticsConvertor.toDO(visitStatistics.get(0));
    }

    @Override
    public int insertVisitStatisticsDO(VisitStatisticsDO visitStatisticsDO) {
        return visitStatisticsMapper.insertSelective(visitStatisticsConvertor.toPO(visitStatisticsDO));
    }

    @Override
    public int updateByPrimaryKeySelective(VisitStatisticsDO visitStatisticsDO) {
        return visitStatisticsMapper.updateByPrimaryKeySelective(visitStatisticsConvertor.toPO(visitStatisticsDO));
    }
}
