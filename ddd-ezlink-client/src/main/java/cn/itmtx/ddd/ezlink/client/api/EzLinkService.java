package cn.itmtx.ddd.ezlink.client.api;

import cn.itmtx.ddd.ezlink.client.dto.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import com.alibaba.cola.dto.SingleResponse;

public interface EzLinkService {

    /**
     * 传入长链接，生成短链接
     * @param urlMapAddCmd
     * @return
     */
    SingleResponse<UrlMapDTO> createUrlMap(UrlMapAddCmd urlMapAddCmd);
}
