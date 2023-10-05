@file:Suppress("DEPRECATION")

package c.hyeoni.tobyreactiveprogramming.step1

import java.util.Observer
import java.util.concurrent.Executors

fun main() {
    val observer = Observer { o, arg -> println("${Thread.currentThread().name} $arg") }

    val intObservable = IntObservable()
    intObservable.addObserver(observer)

    val executorService = Executors.newSingleThreadExecutor()

    executorService.execute(intObservable)

    println("${Thread.currentThread().name} EXIT")
    executorService.shutdown()
}
