package cn.itmtx.ddd.ezlink.component.dl.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 所有对 redisson 的使用都是通过 RedissonClient 来操作的
     * @return
     */
    @Bean(destroyMethod="shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 单 Redis 节点模式
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(String.format("redis://%s:%d", redisProperties.getHost(), redisProperties.getPort()));
        if (redisProperties.getDatabase() > 0) {
            singleServerConfig.setDatabase(redisProperties.getDatabase());
        }
        if (StringUtils.isNotEmpty(redisProperties.getPassword())) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }

}
