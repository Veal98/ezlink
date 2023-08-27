package cn.itmtx.ddd.ezlink.application;

import cn.itmtx.ddd.ezlink.application.executor.UrlMapAddCmdExe;
import cn.itmtx.ddd.ezlink.application.executor.query.DispatchQryCmdExe;
import cn.itmtx.ddd.ezlink.client.api.EzLinkService;
import cn.itmtx.ddd.ezlink.client.dto.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
@CatchAndLog
public class EzLinkServiceImpl implements EzLinkService {

    @Autowired
    private UrlMapAddCmdExe urlMapAddCmdExe;

    @Autowired
    private DispatchQryCmdExe dispatchQryCmdExe;

    @Override
    public SingleResponse<UrlMapDTO> createUrlMap(UrlMapAddCmd urlMapAddCmd) {
        return urlMapAddCmdExe.execute(urlMapAddCmd);
    }

    @Override
    public Mono<Void> dispatch(String compressionCode, ServerWebExchange exchange) {
        return dispatchQryCmdExe.execute(compressionCode, exchange);
    }
}
