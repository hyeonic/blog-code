package io.github.hyeonic.javaconcurrency;

public class VolatileCounter {

    private static volatile int count = 0;

    public void increase() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
