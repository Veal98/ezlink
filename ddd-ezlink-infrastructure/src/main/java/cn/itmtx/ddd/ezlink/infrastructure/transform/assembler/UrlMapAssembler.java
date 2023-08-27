package cn.itmtx.ddd.ezlink.infrastructure.transform.assembler;

import cn.itmtx.ddd.ezlink.domain.UrlMapDO;
import cn.itmtx.ddd.ezlink.infrastructure.transform.po.UrlMap;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UrlMapAssembler {
    public UrlMap fromUrlMapDO(UrlMapDO urlMapDO) {
        UrlMap urlMap = new UrlMap();
        BeanUtils.copyProperties(urlMapDO, urlMap);
        return urlMap;
    }

    public UrlMapDO toUrlMapDO(UrlMap urlMap) {
        UrlMapDO urlMapDO = new UrlMapDO();
        BeanUtils.copyProperties(urlMap, urlMapDO);
        return urlMapDO;
    }
}
