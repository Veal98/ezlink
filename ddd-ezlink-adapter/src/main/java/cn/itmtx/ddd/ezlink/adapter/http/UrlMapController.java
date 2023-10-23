package cn.itmtx.ddd.ezlink.adapter.http;

import cn.itmtx.ddd.ezlink.adapter.constant.API;
import cn.itmtx.ddd.ezlink.client.api.UrlMapService;
import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.client.dto.query.LongByShortQry;
import cn.itmtx.ddd.ezlink.component.ratelimiter.LimitType;
import cn.itmtx.ddd.ezlink.component.ratelimiter.RateLimiter;
import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

@RestController
@Slf4j
@RequestMapping(API.WEB_API_PATH)
public class UrlMapController {

    @Autowired
    private UrlMapService urlMapService;

    /**
     * 传入长链接，创建短链映射
     * @param exchange @RateLimiter 注解要求方法第一个参数是 ServerWebExchange
     * @param urlMapAddCmd
     * @return
     */
    @PostMapping("/create")
    @RateLimiter(count = 3, time = 5, limitType = LimitType.IP)
    public SingleResponse<UrlMapDTO> createUrlMap(ServerWebExchange exchange, @RequestBody UrlMapAddCmd urlMapAddCmd) {
        return urlMapService.createUrlMap(urlMapAddCmd);
    }

    /**
     * 根据短链获取长链
     * @param longByShortQry
     * @return
     */
    public SingleResponse<UrlMapDTO> getLongByShort(@RequestBody LongByShortQry longByShortQry) {
        return urlMapService.getLongByShort(longByShortQry);
    }
}
