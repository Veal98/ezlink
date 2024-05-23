package cn.itmtx.ddd.ezlink.domain.domainservice.token;

import cn.itmtx.ddd.ezlink.domain.exception.TokenUnValidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class TokenDomainService {

    @Value("${ezlink.access.token.check}")
    private Boolean checkAccessToken;

    /**
     * TODO 校验 token
     *
     * @param appId
     * @param appSecret
     * @param accessToken
     */
    public Boolean checkToken(String appId, String appSecret, String accessToken) {
        return true;
    }
}
