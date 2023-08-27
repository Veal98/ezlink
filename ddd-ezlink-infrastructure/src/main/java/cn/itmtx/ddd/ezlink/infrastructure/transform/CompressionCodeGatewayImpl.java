package cn.itmtx.ddd.ezlink.infrastructure.transform;

import cn.itmtx.ddd.ezlink.domain.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.domain.gateway.CompressionCodeGateway;
import cn.itmtx.ddd.ezlink.infrastructure.transform.assembler.CompressionCodeAssembler;
import cn.itmtx.ddd.ezlink.infrastructure.transform.mapper.CompressionCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompressionCodeGatewayImpl implements CompressionCodeGateway {

    @Autowired
    private CompressionCodeMapper compressionCodeMapper;


    @Autowired
    private CompressionCodeAssembler compressionCodeAssembler;

    @Override
    public void insertCompressionCodeDO(CompressionCodeDO compressionCodeDO) {
        compressionCodeMapper.insertSelective(compressionCodeAssembler.fromCompressionCodeDO(compressionCodeDO));
    }

    @Override
    public CompressionCodeDO getLatestAvailableCompressionCodeDO() {
        return compressionCodeMapper.getLatestAvailableCompressionCodeDO();
    }

    @Override
    public int updateByPrimaryKeySelective(CompressionCodeDO compressionCodeDO) {
        return compressionCodeMapper.updateByPrimaryKeySelective(compressionCodeAssembler.fromCompressionCodeDO(compressionCodeDO));
    }
}
