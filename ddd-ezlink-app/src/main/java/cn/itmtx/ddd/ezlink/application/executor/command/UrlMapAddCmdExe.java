package cn.itmtx.ddd.ezlink.application.executor.command;

import cn.itmtx.ddd.ezlink.application.assembler.UrlMapDTOAssembler;
import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;
import cn.itmtx.ddd.ezlink.domain.domainservice.UrlMapDomain;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlMapAddCmdExe {

    @Autowired
    private UrlMapDomain urlMapDomain;

    @Autowired
    private UrlMapDTOAssembler urlMapDTOAssembler;

    public SingleResponse<UrlMapDTO> execute(UrlMapAddCmd urlMapAddCmd) {
        UrlMapDO urlMapDO = urlMapDTOAssembler.toDO(urlMapAddCmd);
        urlMapDomain.createUrlMap(urlMapDO);
        return SingleResponse.of(urlMapDTOAssembler.toDTO(urlMapDO));
    }
}
