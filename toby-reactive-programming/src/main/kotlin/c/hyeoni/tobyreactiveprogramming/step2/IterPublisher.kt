package c.hyeoni.tobyreactiveprogramming.step2

import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.stream.Stream

class IterPublisher(
    val iterator : Iterator<Int> = Stream.iterate(1) { it + 1 }
        .limit(10)
        .iterator()
) : Publisher<Int> {

    override fun subscribe(subscriber: Subscriber<in Int>) {

        subscriber.onSubscribe(

            object : Subscription {

                override fun request(n: Long) {
                    runCatching {
                        iterator.forEach { subscriber.onNext(it) }
                        subscriber.onComplete()
                    }.getOrElse { e -> subscriber.onError(e) }
                }

                override fun cancel() {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}
