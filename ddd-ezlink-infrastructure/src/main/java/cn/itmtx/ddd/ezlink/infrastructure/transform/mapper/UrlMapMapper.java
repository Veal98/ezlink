package cn.itmtx.ddd.ezlink.infrastructure.transform.mapper;

import cn.itmtx.ddd.ezlink.infrastructure.transform.po.UrlMap;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.UrlMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrlMapMapper {
    int deleteByExample(UrlMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UrlMap record);

    int insertSelective(UrlMap record);

    List<UrlMap> selectByExample(UrlMapExample example);

    UrlMap selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UrlMap record, @Param("example") UrlMapExample example);

    int updateByExample(@Param("record") UrlMap record, @Param("example") UrlMapExample example);

    int updateByPrimaryKeySelective(UrlMap record);

    int updateByPrimaryKey(UrlMap record);
}