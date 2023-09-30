package cn.itmtx.ddd.ezlink.infrastructure.transform.convertor;

import cn.itmtx.ddd.ezlink.domain.domainobject.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.CompressionCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * PO <-> DO
 */
@Component
public class CompressionCodeConvertor {

    public CompressionCode toPO(CompressionCodeDO compressionCodeDO) {
        CompressionCode compressionCode = new CompressionCode();
        BeanUtils.copyProperties(compressionCodeDO, compressionCode);
        return compressionCode;
    }

    public CompressionCodeDO toDO(CompressionCode compressionCode) {
        CompressionCodeDO compressionCodeDO = new CompressionCodeDO();
        BeanUtils.copyProperties(compressionCode, compressionCodeDO);
        return compressionCodeDO;
    }
}
