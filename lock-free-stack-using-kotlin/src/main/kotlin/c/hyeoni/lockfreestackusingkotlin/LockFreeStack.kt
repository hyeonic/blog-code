package c.hyeoni.lockfreestackusingkotlin

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.locks.LockSupport


class LockFreeStack<T> {
    private val headNode = AtomicReference<StackNode<*>>()
    val noOfOperations = AtomicInteger(0)

    fun push(value: T) {
        val newHead = StackNode(value)

        // CAS loop defined
        while (true) {
            val currentHeadNode = headNode.get()
            newHead.next = currentHeadNode as StackNode<T>?

            // perform CAS operation before setting new value
            if (headNode.compareAndSet(currentHeadNode, newHead)) {
                break
            } else {
                // waiting for a nanosecond
                LockSupport.parkNanos(1)
            }
        }

        // getting the value atomically
        noOfOperations.incrementAndGet()
    }

    fun pop(): Any? {
        var currentHeadNode: StackNode<*>? = headNode.get()

        // CAS loop defined
        while (currentHeadNode != null) {
            val newHead = currentHeadNode.next
            if (headNode.compareAndSet(currentHeadNode, newHead)) {
                break
            } else {
                LockSupport.parkNanos(1)
                currentHeadNode = headNode.get()
            }
        }
        noOfOperations.incrementAndGet()
        return currentHeadNode?.value
    }

    private class StackNode<T>(var value: T) {
        var next: StackNode<T>? = null
    }
}

