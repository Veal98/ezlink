package cn.itmtx.ddd.ezlink.infrastructure.transform.assembler;

import cn.itmtx.ddd.ezlink.domain.CompressionCodeDO;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.CompressionCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * PO <-> DO
 */
@Component
public class CompressionCodeAssembler {

    public CompressionCode fromCompressionCodeDO(CompressionCodeDO compressionCodeDO) {
        CompressionCode compressionCode = new CompressionCode();
        BeanUtils.copyProperties(compressionCodeDO, compressionCode);
        return compressionCode;
    }

    public CompressionCodeDO toCompressionCodeDO(CompressionCode compressionCode) {
        CompressionCodeDO compressionCodeDO = new CompressionCodeDO();
        BeanUtils.copyProperties(compressionCode, compressionCodeDO);
        return compressionCodeDO;
    }
}
