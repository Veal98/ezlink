package cn.itmtx.ddd.ezlink.application;

import cn.itmtx.ddd.ezlink.application.executor.UrlMapAddCmdExe;
import cn.itmtx.ddd.ezlink.client.api.EzLinkService;
import cn.itmtx.ddd.ezlink.client.dto.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@CatchAndLog
public class EzLinkServiceImpl implements EzLinkService {

    @Autowired
    private UrlMapAddCmdExe urlMapAddCmdExe;

    @Override
    public SingleResponse<UrlMapDTO> createUrlMap(UrlMapAddCmd urlMapAddCmd) {
        return urlMapAddCmdExe.execute(urlMapAddCmd);
    }
}
