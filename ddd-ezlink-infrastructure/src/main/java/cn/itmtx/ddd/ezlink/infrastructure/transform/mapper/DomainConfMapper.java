package cn.itmtx.ddd.ezlink.infrastructure.transform.mapper;

import cn.itmtx.ddd.ezlink.domain.domainobject.DomainConfDO;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.DomainConf;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.DomainConfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DomainConfMapper {
    int deleteByExample(DomainConfExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DomainConf record);

    int insertSelective(DomainConf record);

    List<DomainConf> selectByExample(DomainConfExample example);

    DomainConf selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DomainConf record, @Param("example") DomainConfExample example);

    int updateByExample(@Param("record") DomainConf record, @Param("example") DomainConfExample example);

    int updateByPrimaryKeySelective(DomainConf record);

    int updateByPrimaryKey(DomainConf record);

    DomainConfDO selectDomainConfDOByDomainValue(String domainValue);
}
