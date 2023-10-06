package cn.itmtx.ddd.ezlink.client.dto.query;

import com.alibaba.cola.dto.Query;
import org.springframework.web.server.ServerWebExchange;

public class DispatchQry extends Query {
    private String compressionCode;

    private ServerWebExchange exchange;

    public String getCompressionCode() {
        return compressionCode;
    }

    public void setCompressionCode(String compressionCode) {
        this.compressionCode = compressionCode;
    }

    public ServerWebExchange getExchange() {
        return exchange;
    }

    public void setExchange(ServerWebExchange exchange) {
        this.exchange = exchange;
    }
}
