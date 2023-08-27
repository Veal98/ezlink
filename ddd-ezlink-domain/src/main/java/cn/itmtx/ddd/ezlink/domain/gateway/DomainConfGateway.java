package cn.itmtx.ddd.ezlink.domain.gateway;

import cn.itmtx.ddd.ezlink.domain.DomainConfDO;

public interface DomainConfGateway {

    DomainConfDO getDomainConfDOByDomainValue(String domainValue);
}
