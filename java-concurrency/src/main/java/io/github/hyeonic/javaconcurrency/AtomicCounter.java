package io.github.hyeonic.javaconcurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private static AtomicInteger count = new AtomicInteger(0);

    public void increase() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
