package io.github.hyeonic.javaconcurrency;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

class AtomicCounterTest {

    @Test
    void 동시에_카운터를_증가시킨다() throws InterruptedException {
        AtomicCounter atomicCounter = new AtomicCounter();

        var executorService = Executors.newFixedThreadPool(10);
        var countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    atomicCounter.increase();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        assertThat(atomicCounter.getCount()).isEqualTo(100);
    }
}
