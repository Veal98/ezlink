package cn.itmtx.ddd.ezlink.infrastructure.transform.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.domain.gateway.CompressionCodeGateway;
import cn.itmtx.ddd.ezlink.infrastructure.transform.convertor.CompressionCodeConvertor;
import cn.itmtx.ddd.ezlink.infrastructure.transform.mapper.CompressionCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompressionCodeGatewayImpl implements CompressionCodeGateway {

    @Autowired
    private CompressionCodeMapper compressionCodeMapper;


    @Autowired
    private CompressionCodeConvertor compressionCodeConvertor;

    @Override
    public void insertCompressionCodeDO(CompressionCodeDO compressionCodeDO) {
        compressionCodeMapper.insertSelective(compressionCodeConvertor.toPO(compressionCodeDO));
    }

    @Override
    public CompressionCodeDO getLatestAvailableCompressionCodeDO() {
        return compressionCodeMapper.getLatestAvailableCompressionCodeDO();
    }

    @Override
    public int updateByPrimaryKeySelective(CompressionCodeDO compressionCodeDO) {
        return compressionCodeMapper.updateByPrimaryKeySelective(compressionCodeConvertor.toPO(compressionCodeDO));
    }

    @Override
    public List<String> selectAllCompressionCode() {
        return compressionCodeMapper.selectAllCompressionCode();
    }
}
