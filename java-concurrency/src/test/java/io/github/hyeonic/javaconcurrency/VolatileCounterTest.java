package io.github.hyeonic.javaconcurrency;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

public class VolatileCounterTest {

    @Test
    void 동시에_카운터를_증가시킨다() throws InterruptedException {
        VolatileCounter volatileCounter = new VolatileCounter();

        var executorService = Executors.newFixedThreadPool(10);
        var countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    volatileCounter.increase();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        assertThat(volatileCounter.getCount()).isEqualTo(100);
    }
}
