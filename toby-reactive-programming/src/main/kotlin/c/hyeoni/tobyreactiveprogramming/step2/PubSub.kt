package c.hyeoni.tobyreactiveprogramming.step2

import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import java.util.stream.Stream

/**
 * Publisher -> [Data1] -> Operator1 -> [Data2] -> Operator2 -> [Data3] -> Subscriber
 */

fun main() {

    val publisher = IterPublisher(
        Stream.iterate(1) { it + 1 }
            .limit(10)
            .iterator()
    )
//    val mapPublisher1 = mapPublisher(publisher) { it * 10 }
//    val mapPublisher2 = mapPublisher(mapPublisher1) { -it }
    val sumPublisher = sumPublisher(publisher)

    sumPublisher.subscribe(LogSubscriber())
}

fun mapPublisher(publisher: Publisher<Int>, apply: (Int) -> Int): Publisher<Int> {
    return object : Publisher<Int> {

        override fun subscribe(subscriber: Subscriber<in Int>) {
            publisher.subscribe(
                object : DelegateSubscriber(subscriber) {

                    override fun onNext(t: Int) {
                        subscriber.onNext(apply(t))
                    }
                }
            )
        }
    }
}

fun sumPublisher(publisher: Publisher<Int>): Publisher<Int> {
    return object : Publisher<Int> {

        override fun subscribe(subscriber: Subscriber<in Int>) {
            publisher.subscribe(
                object : DelegateSubscriber(subscriber) {

                    var sum = 0

                    override fun onNext(t: Int) {
                        sum += t
                    }

                    override fun onComplete() {
                        subscriber.onNext(sum)
                        subscriber.onComplete()
                    }
                }
            )
        }
    }
}

fun reducePublisher(publisher: Publisher<Int>, initNumber: Int, apply: (Int, Int) -> Int): Publisher<Int> {

}
