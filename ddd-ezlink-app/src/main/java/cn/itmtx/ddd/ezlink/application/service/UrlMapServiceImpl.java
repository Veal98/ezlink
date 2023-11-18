package cn.itmtx.ddd.ezlink.application.service;

import cn.itmtx.ddd.ezlink.application.executor.command.UrlMapAddCmdExe;
import cn.itmtx.ddd.ezlink.client.api.UrlMapService;
import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.client.dto.query.LongByShortQry;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


/**
 * 这里起个 BeanName="urlMapService"，不然自动注入的时候会和 UrlMapProvider 冲突
 * 或者用 @Primary 注解也可以
 */
@Service
@Primary
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
