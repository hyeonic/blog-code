package io.github.hyeonic.javaconcurrency;

public class SynchronizedCounter {

    private static int count = 0;

    public synchronized void increase() {
        count++;
    }

    public void increaseWithBody() {
        synchronized (this) {
            count++;
        }
    }

    public static void increaseWithStatic() {
        synchronized (SynchronizedCounter.class) {
            count++;
        }
    }

    public static int getCount() {
        return count;
    }
}
