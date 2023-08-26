package cn.itmtx.ddd.ezlink.infrastructure.transform;

import cn.itmtx.ddd.ezlink.domain.transform.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.transform.gateway.UrlMapGateway;
import cn.itmtx.ddd.ezlink.infrastructure.transform.assembler.UrlMapAssembler;
import cn.itmtx.ddd.ezlink.infrastructure.transform.mapper.UrlMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlMapGatewayImpl implements UrlMapGateway {

    @Autowired
    private UrlMapMapper urlMapMapper;

    @Autowired
    private UrlMapAssembler urlMapAssembler;

    @Override
    public int insertUrlMapDO(UrlMapDO urlMapDO) {
        return urlMapMapper.insertSelective(urlMapAssembler.fromUrlMapDO(urlMapDO));
    }
}
