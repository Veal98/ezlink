package cn.itmtx.ddd.ezlink.infrastructure.transform.assembler;

import cn.itmtx.ddd.ezlink.domain.DomainConfDO;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.DomainConf;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DomainConfAssembler {

    public DomainConf fromDomainConfDO(DomainConfDO domainConfDO) {
        DomainConf domainConf = new DomainConf();
        BeanUtils.copyProperties(domainConfDO, domainConf);
        return domainConf;
    }

    public DomainConfDO toCompressionCodeDO(DomainConf domainConf) {
        DomainConfDO domainConfDO = new DomainConfDO();
        BeanUtils.copyProperties(domainConf, domainConfDO);
        return domainConfDO;
    }
}
