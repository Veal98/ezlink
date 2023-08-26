package cn.itmtx.ddd.ezlink.infrastructure.transform;

import cn.itmtx.ddd.ezlink.domain.transform.DomainConfDO;
import cn.itmtx.ddd.ezlink.domain.transform.gateway.DomainConfGateway;
import cn.itmtx.ddd.ezlink.infrastructure.transform.assembler.DomainConfAssembler;
import cn.itmtx.ddd.ezlink.infrastructure.transform.mapper.DomainConfMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomainConfGatewayImpl implements DomainConfGateway {

    @Autowired
    private DomainConfMapper domainConfMapper;

    @Override
    public DomainConfDO getDomainConfDOByDomainValue(String domainValue) {
        return domainConfMapper.selectDomainConfDOByDomainValue(domainValue);
    }
}
