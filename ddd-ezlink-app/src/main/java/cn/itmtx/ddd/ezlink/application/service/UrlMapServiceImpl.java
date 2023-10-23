package cn.itmtx.ddd.ezlink.application.service;

import cn.itmtx.ddd.ezlink.application.executor.command.UrlMapAddCmdExe;
import cn.itmtx.ddd.ezlink.client.api.UrlMapService;
import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.client.dto.query.LongByShortQry;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlMapServiceImpl implements UrlMapService {

    @Autowired
    private UrlMapAddCmdExe urlMapAddCmdExe;

    @Override
    public SingleResponse<UrlMapDTO> createUrlMap(UrlMapAddCmd urlMapAddCmd) {
        return urlMapAddCmdExe.execute(urlMapAddCmd);
    }

    @Override
    public SingleResponse<UrlMapDTO> getLongByShort(LongByShortQry longByShortQry) {
        // TODO
        return SingleResponse.of(null);
    }
}
