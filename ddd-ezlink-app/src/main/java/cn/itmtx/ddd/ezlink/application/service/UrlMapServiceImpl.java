package cn.itmtx.ddd.ezlink.application.service;

import cn.itmtx.ddd.ezlink.application.executor.command.UrlMapAddCmdExe;
import cn.itmtx.ddd.ezlink.client.api.UrlMapService;
import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDto;
import cn.itmtx.ddd.ezlink.client.dto.query.LongByShortQry;
import cn.itmtx.ddd.ezlink.domain.domainservice.token.TokenDomainService;
import com.alibaba.cola.dto.SingleResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * 这里起个 BeanName="urlMapService"，不然自动注入的时候会和 UrlMapProvider 冲突
 * 或者用 @Primary 注解也可以
 */
@Service
@Primary
public class UrlMapServiceImpl implements UrlMapService {

    @Autowired
    private UrlMapAddCmdExe urlMapAddCmdExe;

    @Autowired
    private TokenDomainService tokenDomainService;

    @Override
    public SingleResponse<UrlMapDto> createUrlMap(UrlMapAddCmd urlMapAddCmd) {
        if (Objects.isNull(urlMapAddCmd) || StringUtils.isEmpty(urlMapAddCmd.getLongUrl()) || StringUtils.isEmpty(urlMapAddCmd.getDescription())) {
            throw new IllegalArgumentException("longUrl and description can't be empty.");
        }

        return urlMapAddCmdExe.execute(urlMapAddCmd);
    }

    @Override
    public SingleResponse<UrlMapDto> getLongByShort(LongByShortQry longByShortQry) {
        // TODO
        return SingleResponse.of(null);
    }
}
