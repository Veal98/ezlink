package cn.itmtx.ddd.ezlink.client.api;

import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.client.dto.query.DispatchQry;
import com.alibaba.cola.dto.SingleResponse;
import reactor.core.publisher.Mono;

public interface DispatchService {

    /**
     * 短链重定向
     * @param dispatchQry
     * @return
     */
    Mono<Void> dispatch(DispatchQry dispatchQry);
}
