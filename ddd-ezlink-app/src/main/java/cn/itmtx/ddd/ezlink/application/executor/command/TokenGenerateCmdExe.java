package cn.itmtx.ddd.ezlink.application.executor.command;

import cn.itmtx.ddd.ezlink.client.dto.command.TokenGenerateCmd;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.stereotype.Component;

/**
 * @Author jc.yin
 * @Date 2024/5/23
 * @Description
 **/
@Component
public class TokenGenerateCmdExe {

    public SingleResponse<String> execute(TokenGenerateCmd tokenGenerateCmd) {
        return null;
    }
}
