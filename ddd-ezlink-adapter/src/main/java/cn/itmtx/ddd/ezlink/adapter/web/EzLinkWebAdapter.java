package cn.itmtx.ddd.ezlink.adapter.web;

import cn.itmtx.ddd.ezlink.client.api.EzLinkService;
import cn.itmtx.ddd.ezlink.client.dto.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class EzLinkWebAdapter {

    @Autowired
    private EzLinkService ezLinkService;

    /**
     * 长链重定向
     * @param compressionCode
     * @param exchange
     * @return
     */
    @GetMapping("/{compressionCode}")
    public Mono<Void> dispatch(@PathVariable(name="compressionCode") String compressionCode, ServerWebExchange exchange) {
        return ezLinkService.dispatch(compressionCode, exchange);
    }

    /**
     * 传入长链接，创建短链映射
     */
    @PostMapping("/create")
    public SingleResponse<UrlMapDTO> createUrlMap(@RequestBody UrlMapAddCmd urlMapAddCmd) {
        return ezLinkService.createUrlMap(urlMapAddCmd);
    }
}
