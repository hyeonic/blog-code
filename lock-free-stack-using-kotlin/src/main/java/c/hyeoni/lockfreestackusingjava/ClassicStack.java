package c.hyeoni.lockfreestackusingjava;

public class ClassicStack<T> {

    private StackNode<T> headNode;
    private int noOfOperations;

    public synchronized int getNoOfOperations() {
        return noOfOperations;
    }

    public synchronized void push(T number) {
        StackNode<T> newNode = new StackNode<>(number);
        newNode.next = headNode;
        headNode = newNode;
        noOfOperations++;
    }

    public synchronized T pop() {
        if (headNode == null) {
            return null;
        } else {
            T val = headNode.getValue();
            headNode = headNode.next;
            noOfOperations++;
            return val;
        }
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
