package cn.itmtx.ddd.ezlink.application.executor.command;

import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.domain.domainservice.UrlMapDomain;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlMapAddCmdExe {

    @Autowired
    private UrlMapDomain urlMapDomain;

    public SingleResponse<UrlMapDTO> execute(UrlMapAddCmd urlMapAddCmd) {
        UrlMapDTO urlMapDTO = urlMapDomain.createUrlMap(urlMapAddCmd);
        return SingleResponse.of(urlMapDTO);
    }
}
