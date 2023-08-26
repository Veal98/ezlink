package cn.itmtx.ddd.ezlink.domain.transform.gateway;

import cn.itmtx.ddd.ezlink.domain.transform.DomainConfDO;

public interface DomainConfGateway {

    DomainConfDO getDomainConfDOByDomainValue(String domainValue);
}
