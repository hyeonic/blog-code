package c.hyeoni.tobyreactiveprogramming.step1

import java.util.Observable

@Suppress("DEPRECATION")
class IntObservable: Observable(), Runnable {

    override fun run() {
        IntRange(1, 10).forEach {
            setChanged()
            notifyObservers(it) // push
        }
    }
}
