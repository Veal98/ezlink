package cn.itmtx.ddd.ezlink.infrastructure.transform.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.domain.enums.CompressionCodeStatusEnum;
import cn.itmtx.ddd.ezlink.domain.gateway.CompressionCodeGateway;
import cn.itmtx.ddd.ezlink.infrastructure.transform.convertor.CompressionCodeConvertor;
import cn.itmtx.ddd.ezlink.infrastructure.transform.mapper.CompressionCodeMapper;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.CompressionCode;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.CompressionCodeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 获取所有已使用的 compressionCode
     * @return
     */
    @Override
    public List<String> getAllUsedCompressionCode() {
        CompressionCodeExample example = new CompressionCodeExample();
        example.or().andCodeStatusEqualTo(CompressionCodeStatusEnum.USED.getValue());
        List<CompressionCode> compressionCodes = compressionCodeMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(compressionCodes)) {
            return new ArrayList<>();
        }

        return compressionCodes.stream().map(CompressionCode::getCompressionCode).collect(Collectors.toList());
    }

    @Override
    public int updateByPrimaryKeySelective(CompressionCodeDO compressionCodeDO) {
        return compressionCodeMapper.updateByPrimaryKeySelective(compressionCodeConvertor.toPO(compressionCodeDO));
    }
}
