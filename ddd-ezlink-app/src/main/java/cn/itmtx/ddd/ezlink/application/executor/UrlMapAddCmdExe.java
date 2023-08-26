package cn.itmtx.ddd.ezlink.application.executor;

import cn.itmtx.ddd.ezlink.client.dto.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.domain.transform.domainservice.CompressionCodeDomain;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlMapAddCmdExe {

    @Autowired
    private CompressionCodeDomain compressionCodeDomain;

    public SingleResponse<UrlMapDTO> execute(UrlMapAddCmd urlMapAddCmd) {
        UrlMapDTO urlMapDTO = compressionCodeDomain.createUrlMap(urlMapAddCmd);
        return SingleResponse.of(urlMapDTO);
    }
}
