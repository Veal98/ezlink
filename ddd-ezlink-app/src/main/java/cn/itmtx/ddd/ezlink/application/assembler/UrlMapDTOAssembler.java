package cn.itmtx.ddd.ezlink.application.assembler;

import cn.itmtx.ddd.ezlink.client.dto.command.UrlMapAddCmd;
import cn.itmtx.ddd.ezlink.client.dto.data.UrlMapDto;
import cn.itmtx.ddd.ezlink.domain.domainobject.UrlMapDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * DO <-> DTO
 */
@Component
public class UrlMapDTOAssembler {
    public UrlMapDO toDO(UrlMapDto urlMapDTO) {
        UrlMapDO urlMapDO = new UrlMapDO();
        BeanUtils.copyProperties(urlMapDTO, urlMapDO);
        return urlMapDO;
    }

    public UrlMapDto toDTO(UrlMapDO urlMapDO) {
        UrlMapDto urlMapDTO = new UrlMapDto();
        BeanUtils.copyProperties(urlMapDO, urlMapDTO);
        return urlMapDTO;
    }

    public UrlMapDO toDO(UrlMapAddCmd urlMapAddCmd) {
        UrlMapDO urlMapDO = new UrlMapDO();
        BeanUtils.copyProperties(urlMapAddCmd, urlMapDO);
        return urlMapDO;
    }
}
