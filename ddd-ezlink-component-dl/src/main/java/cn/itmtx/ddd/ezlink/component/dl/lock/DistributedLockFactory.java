package cn.itmtx.ddd.ezlink.component.dl.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistributedLockFactory {

    public static final String DISTRIBUTED_LOCK_PATH_PREFIX = "dl:";

    private final RedissonClient redissonClient;


    public DistributedLockFactory(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 创建分布式锁
     * @param lockKey key 相同则是同一把锁
     * @return
     */
    public RLock getLock(String lockKey) {
        String lockPath = DISTRIBUTED_LOCK_PATH_PREFIX + lockKey;
        return redissonClient.getLock(lockPath);
    }
}
