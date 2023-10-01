package cn.itmtx.ddd.ezlink.domain.domainobject;

import com.alibaba.cola.domain.Entity;

@Entity
public class DomainConfDO {
    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
