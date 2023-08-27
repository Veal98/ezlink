package cn.itmtx.ddd.ezlink.infrastructure.record.mapper;

import cn.itmtx.ddd.ezlink.infrastructure.record.po.TransformEventRecord;
import cn.itmtx.ddd.ezlink.infrastructure.record.po.TransformEventRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransformEventRecordMapper {
    int deleteByExample(TransformEventRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TransformEventRecord record);

    int insertSelective(TransformEventRecord record);

    List<TransformEventRecord> selectByExample(TransformEventRecordExample example);

    TransformEventRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TransformEventRecord record, @Param("example") TransformEventRecordExample example);

    int updateByExample(@Param("record") TransformEventRecord record, @Param("example") TransformEventRecordExample example);

    int updateByPrimaryKeySelective(TransformEventRecord record);

    int updateByPrimaryKey(TransformEventRecord record);
}
