package cn.itmtx.ddd.ezlink.infrastructure.transform.convertor;

import cn.itmtx.ddd.ezlink.domain.domainobject.DomainConfDO;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.DomainConf;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DomainConfConvertor {

    public DomainConf toPO(DomainConfDO domainConfDO) {
        DomainConf domainConf = new DomainConf();
        BeanUtils.copyProperties(domainConfDO, domainConf);
        return domainConf;
    }

    public DomainConfDO toDO(DomainConf domainConf) {
        DomainConfDO domainConfDO = new DomainConfDO();
        BeanUtils.copyProperties(domainConf, domainConfDO);
        return domainConfDO;
    }
}
