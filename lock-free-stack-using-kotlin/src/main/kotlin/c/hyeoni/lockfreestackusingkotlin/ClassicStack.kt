package c.hyeoni.lockfreestackusingkotlin


class ClassicStack<T> {
    private var headNode: StackNode<T>? = null

    @get:Synchronized
    var noOfOperations = 0
        private set

    @Synchronized
    fun push(number: T) {
        val newNode = StackNode(number)
        newNode.next = headNode
        headNode = newNode
        noOfOperations++
    }

    @Synchronized
    fun pop(): T? {
        return if (headNode == null) {
            null
        } else {
            val value = headNode!!.value
            headNode = headNode!!.next
            noOfOperations++
            value
        }
    }

    private class StackNode<T>(var value: T) {
        var next: StackNode<T>? = null
    }
}

