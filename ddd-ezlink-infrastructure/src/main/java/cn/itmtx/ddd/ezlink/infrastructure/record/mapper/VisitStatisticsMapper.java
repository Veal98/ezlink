package cn.itmtx.ddd.ezlink.infrastructure.record.mapper;

import cn.itmtx.ddd.ezlink.infrastructure.record.po.VisitStatistics;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.VisitStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VisitStatisticsMapper {
    int deleteByExample(VisitStatisticsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VisitStatistics record);

    int insertSelective(VisitStatistics record);

    List<VisitStatistics> selectByExample(VisitStatisticsExample example);

    VisitStatistics selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VisitStatistics record, @Param("example") VisitStatisticsExample example);

    int updateByExample(@Param("record") VisitStatistics record, @Param("example") VisitStatisticsExample example);

    int updateByPrimaryKeySelective(VisitStatistics record);

    int updateByPrimaryKey(VisitStatistics record);
}