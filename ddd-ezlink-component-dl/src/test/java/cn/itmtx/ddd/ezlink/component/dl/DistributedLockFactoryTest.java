package cn.itmtx.ddd.ezlink.component.dl;

import cn.itmtx.ddd.ezlink.component.dl.lock.DistributedLockFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DlApplication.class)
public class DistributedLockFactoryTest {

    @Autowired
    private DistributedLockFactory distributedLockFactory;

    @Test
    public void testLock() {
        RLock lock = distributedLockFactory.getLock("test");
        try {
            lock.lock(3000, TimeUnit.MILLISECONDS);
            System.out.println("lock success");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("unlock success");
        }
    }
}
