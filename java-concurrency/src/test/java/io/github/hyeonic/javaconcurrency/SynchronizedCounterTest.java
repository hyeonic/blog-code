package io.github.hyeonic.javaconcurrency;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

public class SynchronizedCounterTest {

    @Test
    void 동시에_카운터를_증가시킨다() throws InterruptedException {
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

        var executorService = Executors.newFixedThreadPool(10);
        var countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    synchronizedCounter.increase();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        assertThat(SynchronizedCounter.getCount()).isEqualTo(100);
    }
}
