package cn.itmtx.ddd.ezlink.domain.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.DomainConfDO;

public interface DomainConfGateway {

    DomainConfDO getDomainConfDOByDomainValue(String domainValue);
}
