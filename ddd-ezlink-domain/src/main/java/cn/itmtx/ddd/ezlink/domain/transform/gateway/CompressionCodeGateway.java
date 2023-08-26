package cn.itmtx.ddd.ezlink.domain.transform.gateway;

import cn.itmtx.ddd.ezlink.domain.transform.CompressionCodeDO;

public interface CompressionCodeGateway {

    void insertCompressionCodeDO(CompressionCodeDO compressionCodeDO);

    /**
     * 获取最早创建（create_time）且 code_status = 1（未使用）的压缩码
     * @return
     */
    CompressionCodeDO getLatestAvailableCompressionCodeDO();

    int updateByPrimaryKeySelective(CompressionCodeDO compressionCodeDO);
}
