package cn.itmtx.ddd.ezlink.infrastructure.transform.convertor;

import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.UrlMap;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UrlMapConvertor {
    public UrlMap toPO(UrlMapDO urlMapDO) {
        UrlMap urlMap = new UrlMap();
        BeanUtils.copyProperties(urlMapDO, urlMap);
        return urlMap;
    }

    public UrlMapDO toDO(UrlMap urlMap) {
        UrlMapDO urlMapDO = new UrlMapDO();
        BeanUtils.copyProperties(urlMap, urlMapDO);
        return urlMapDO;
    }
}
