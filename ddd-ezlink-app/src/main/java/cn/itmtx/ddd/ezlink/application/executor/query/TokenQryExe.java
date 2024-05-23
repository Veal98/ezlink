package cn.itmtx.ddd.ezlink.application.executor.query;

import cn.itmtx.ddd.ezlink.client.dto.query.TokenQry;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenQryExe {

    public SingleResponse<Boolean> execute(TokenQry tokenQry) {
        return SingleResponse.of(true);
    }
}
