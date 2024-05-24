package cn.itmtx.ddd.ezlink.application.service;

import cn.itmtx.ddd.ezlink.application.executor.query.DispatchQryExe;
import cn.itmtx.ddd.ezlink.client.api.DispatchService;
import cn.itmtx.ddd.ezlink.client.dto.query.DispatchQry;
import com.alibaba.cola.dto.SingleResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private DispatchQryExe dispatchQryExe;

    @Override
    public Mono<Void> dispatch(DispatchQry dispatchQry) {
        if (Objects.isNull(dispatchQry) || StringUtils.isEmpty(dispatchQry.getCompressionCode())) {
            throw new IllegalArgumentException("compressionCode can't be empty.");
        }
        return dispatchQryExe.execute(dispatchQry);
    }

}
