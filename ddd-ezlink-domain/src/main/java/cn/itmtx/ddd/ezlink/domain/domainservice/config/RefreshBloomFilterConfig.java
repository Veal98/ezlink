package cn.itmtx.ddd.ezlink.domain.domainservice.config;

import cn.itmtx.ddd.ezlink.domain.gateway.CompressionCodeGateway;
import com.google.common.hash.BloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Configuration
public class RefreshBloomFilterConfig implements InitializingBean {

    @Autowired
    @Qualifier("compressionCodeBloom")
    private BloomFilter<String> compressionCodeBloom;

    @Autowired
    private CompressionCodeGateway compressionCodeGateway;

    /**
     * 启动项目时把所有 compressionCode 插入布隆过滤器
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> allUsedCompressionCode = compressionCodeGateway.selectAllCompressionCode();
        if (CollectionUtils.isEmpty(allUsedCompressionCode)) {
            log.info("There are no compressionCodes can be stored in bloomFilter.");
            return ;
        }

        // 存到 BloomFilter
        allUsedCompressionCode.forEach(compressionCode -> compressionCodeBloom.put(compressionCode));
        log.info("There are [{}] compressionCodes has stored in bloomFilter.", allUsedCompressionCode.size());
    }
}
