package cn.itmtx.ddd.ezlink.domain.gateway;

import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;

public interface UrlMapGateway {

    int insertUrlMapDO(UrlMapDO urlMapDO);

    /**
     * 通过压缩码查找
     * @param compressionCode
     * @return
     */
    UrlMapDO getUrlMapDOByCompressionCode(String compressionCode);
}
