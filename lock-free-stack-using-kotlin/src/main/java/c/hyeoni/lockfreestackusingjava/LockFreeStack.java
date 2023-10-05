package c.hyeoni.lockfreestackusingjava;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> {

    private AtomicReference<StackNode> headNode = new AtomicReference<>();
    private AtomicInteger noOfOperations = new AtomicInteger(0);

    public AtomicInteger getNoOfOperations() {
        return noOfOperations;
    }

    public void push(T value) {
        StackNode<T> newHead = new StackNode<>(value);

        // CAS loop defined
        while (true) {
            StackNode currentHeadNode = headNode.get();
            newHead.next = currentHeadNode;

            // perform CAS operation before setting new value
            if (headNode.compareAndSet(currentHeadNode, newHead)) {
                break;
            } else {
                // waiting for a nanosecond
                LockSupport.parkNanos(1);
            }
        }

        // getting the value atomically
        noOfOperations.incrementAndGet();
    }

    public T pop() {
        StackNode<T> currentHeadNode = headNode.get();

        // CAS loop defined
        while (currentHeadNode != null) {
            StackNode<T> newHead = currentHeadNode.next;
            if (headNode.compareAndSet(currentHeadNode, newHead)) {
                break;
            } else {
                LockSupport.parkNanos(1);
                currentHeadNode = headNode.get();
            }
        }
        noOfOperations.incrementAndGet();
        return currentHeadNode != null ? currentHeadNode.value : null;
    }

    private static class StackNode<T> {

        T value;
        StackNode<T> next;

        public StackNode(final T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}
