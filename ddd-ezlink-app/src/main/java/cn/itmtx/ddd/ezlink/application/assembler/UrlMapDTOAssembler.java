package cn.itmtx.ddd.ezlink.application.assembler;

import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDTO;
import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * DO <-> DTO
 */
@Component
public class UrlMapDTOAssembler {
    public UrlMapDO toDO(UrlMapDTO urlMapDTO) {
        UrlMapDO urlMapDO = new UrlMapDO();
        BeanUtils.copyProperties(urlMapDTO, urlMapDO);
        return urlMapDO;
    }

    public UrlMapDTO toDTO(UrlMapDO urlMapDO) {
        UrlMapDTO urlMapDTO = new UrlMapDTO();
        BeanUtils.copyProperties(urlMapDO, urlMapDTO);
        return urlMapDTO;
    }

    public UrlMapDO toDO(UrlMapAddCmd urlMapAddCmd) {
        UrlMapDO urlMapDO = new UrlMapDO();
        BeanUtils.copyProperties(urlMapAddCmd, urlMapDO);
        return urlMapDO;
    }
}
