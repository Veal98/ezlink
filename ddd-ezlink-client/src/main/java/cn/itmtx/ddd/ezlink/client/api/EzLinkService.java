package cn.itmtx.ddd.ezlink.client.api;

import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.client.dto.query.DisPatchQry;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface EzLinkService {

    /**
     * 短链重定向
     * @param dispatchQry
     * @return
     */
    Mono<Void> dispatch(DisPatchQry dispatchQry);

    /**
     * 传入长链接，生成短链接
     * @param urlMapAddCmd
     * @return
     */
    SingleResponse<UrlMapDTO> createUrlMap(UrlMapAddCmd urlMapAddCmd);
}
