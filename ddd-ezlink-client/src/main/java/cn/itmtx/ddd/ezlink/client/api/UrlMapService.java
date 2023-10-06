package cn.itmtx.ddd.ezlink.client.api;

import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.client.dto.query.DispatchQry;
import com.alibaba.cola.dto.SingleResponse;
import reactor.core.publisher.Mono;

public interface UrlMapService {

    /**
     * 传入长链接，生成短链接
     * @param urlMapAddCmd
     * @return
     */
    SingleResponse<UrlMapDTO> createUrlMap(UrlMapAddCmd urlMapAddCmd);
}
