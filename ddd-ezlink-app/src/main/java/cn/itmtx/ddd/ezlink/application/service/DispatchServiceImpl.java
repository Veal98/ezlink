package cn.itmtx.ddd.ezlink.application.service;

import cn.itmtx.ddd.ezlink.application.executor.query.DispatchQryExe;
import cn.itmtx.ddd.ezlink.client.api.DispatchService;
import cn.itmtx.ddd.ezlink.client.dto.query.DispatchQry;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchQryExe dispatchQryExe;

    @Override
    public Mono<Void> dispatch(DispatchQry dispatchQry) {
        return dispatchQryExe.execute(dispatchQry);
    }

}
