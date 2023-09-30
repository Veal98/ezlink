package cn.itmtx.ddd.ezlink.infrastructure.transform.mapper;

import cn.itmtx.ddd.ezlink.domain.domainobject.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.CompressionCode;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.CompressionCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompressionCodeMapper {
    int deleteByExample(CompressionCodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CompressionCode record);

    int insertSelective(CompressionCode record);

    List<CompressionCode> selectByExample(CompressionCodeExample example);

    CompressionCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CompressionCode record, @Param("example") CompressionCodeExample example);

    int updateByExample(@Param("record") CompressionCode record, @Param("example") CompressionCodeExample example);

    int updateByPrimaryKeySelective(CompressionCode record);

    int updateByPrimaryKey(CompressionCode record);

    /**
     * 获取最早创建（create_time）且 code_status = 1（未使用）的压缩码
     * @return
     */
    CompressionCodeDO getLatestAvailableCompressionCodeDO();

    /**
     * 获取所有 compressionCode
     * @return
     */
    List<String> selectAllCompressionCode();
}
