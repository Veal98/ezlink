package cn.itmtx.ddd.ezlink.infrastructure.transform.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.gateway.UrlMapGateway;
import cn.itmtx.ddd.ezlink.infrastructure.transform.convertor.UrlMapConvertor;
import cn.itmtx.ddd.ezlink.infrastructure.transform.mapper.UrlMapMapper;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.UrlMap;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.UrlMapExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UrlMapGatewayImpl implements UrlMapGateway {

    @Autowired
    private UrlMapMapper urlMapMapper;

    @Autowired
    private UrlMapConvertor urlMapConvertor;

    @Override
    public int insertUrlMapDO(UrlMapDO urlMapDO) {
        return urlMapMapper.insertSelective(urlMapConvertor.toPO(urlMapDO));
    }

    @Override
    public UrlMapDO getUrlMapDOByCompressionCode(String compressionCode) {
        UrlMapExample urlMapExample = new UrlMapExample();
        urlMapExample.or().andCompressionCodeEqualTo(compressionCode);
        Optional<UrlMap> urlMap = urlMapMapper.selectByExample(urlMapExample).stream().findFirst();
        return urlMap.isPresent() ? urlMapConvertor.toDO(urlMap.get()) : null;
    }
}
