package cn.itmtx.ddd.ezlink.client.api;

import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDto;
import cn.itmtx.ddd.ezlink.client.dto.query.LongByShortQry;
import com.alibaba.cola.dto.SingleResponse;

public interface UrlMapService {

    /**
     * 传入长链接，生成短链接
     * @param urlMapAddCmd
     * @return
     */
    SingleResponse<UrlMapDto> createUrlMap(UrlMapAddCmd urlMapAddCmd);

    /**
     * 根据短链接获取长链接
     * @param longByShortQry
     * @return
     */
    SingleResponse<UrlMapDto> getLongByShort(LongByShortQry longByShortQry);
}
