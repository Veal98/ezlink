package cn.itmtx.ddd.ezlink.domain.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.VisitStatisticsDO;

import java.util.Date;

public interface VisitStatisticsGateway {

    VisitStatisticsDO selectByUniqueKey(Date statisticsDate, String compressionCode, String shortUrl, String longUrl);

    int insertVisitStatisticsDO(VisitStatisticsDO visitStatisticsDO);

    int updateByPrimaryKeySelective(VisitStatisticsDO visitStatisticsDO);
}
