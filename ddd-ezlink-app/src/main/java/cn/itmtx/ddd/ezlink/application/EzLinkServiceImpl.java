package cn.itmtx.ddd.ezlink.application;

import cn.itmtx.ddd.ezlink.application.executor.command.UrlMapAddCmdExe;
import cn.itmtx.ddd.ezlink.application.executor.query.DispatchQryExe;
import cn.itmtx.ddd.ezlink.client.api.EzLinkService;
import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.client.dto.query.DisPatchQry;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@CatchAndLog
public class EzLinkServiceImpl implements EzLinkService {

    @Autowired
    private UrlMapAddCmdExe urlMapAddCmdExe;

    @Autowired
    private DispatchQryExe dispatchQryExe;

    @Override
    public Mono<Void> dispatch(DisPatchQry dispatchQry) {
        return dispatchQryExe.execute(dispatchQry);
    }

    @Override
    public SingleResponse<UrlMapDTO> createUrlMap(UrlMapAddCmd urlMapAddCmd) {
        return urlMapAddCmdExe.execute(urlMapAddCmd);
    }
}
