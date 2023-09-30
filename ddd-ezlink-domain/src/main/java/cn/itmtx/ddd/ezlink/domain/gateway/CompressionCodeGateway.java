package cn.itmtx.ddd.ezlink.domain.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.CompressionCodeDO;

import java.util.List;

public interface CompressionCodeGateway {

    void insertCompressionCodeDO(CompressionCodeDO compressionCodeDO);

    /**
     * 获取最早创建（create_time）且 code_status = 1（未使用）的压缩码
     * @return
     */
    CompressionCodeDO getLatestAvailableCompressionCodeDO();

    int updateByPrimaryKeySelective(CompressionCodeDO compressionCodeDO);

    /**
     * 获取所有 compressionCode
     * @return
     */
    List<String> selectAllCompressionCode();
}
