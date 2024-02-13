package cn.itmtx.ddd.ezlink;

import cn.itmtx.ddd.ezlink.start.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

/**
 * 集成测试，需要确保环境都正常启动
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MonoTest {

    @Test
    public void testMonoFromRunnable() {
        Mono.fromRunnable(createRunnable()).subscribe();
    }

    private Runnable createRunnable() {
        return () -> System.out.println("hello world");
    }
}
