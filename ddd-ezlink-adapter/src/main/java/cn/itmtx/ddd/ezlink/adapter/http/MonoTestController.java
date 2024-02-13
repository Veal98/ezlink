package cn.itmtx.ddd.ezlink.adapter.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Mono 返回值测试
 */
@RestController
public class MonoTestController {

    /**
     * 测试 Mono.fromRunnable 异步构建
     * @return
     */
    @GetMapping("/test")
    public Mono<Void> testMonoFromRunnable() {
        System.out.println("Main Thread: " + Thread.currentThread().getId());

        // 延迟，否则逻辑太简单不会开异步线程
        return Mono.delay(Duration.ofMillis(100))
                .doOnNext(delay -> System.out.println("Delay Thread: " + Thread.currentThread().getId()))
                .then(Mono.fromRunnable(() -> {
                    System.out.println("Runnable Thread: " + Thread.currentThread().getId());
                }));
    }
}
