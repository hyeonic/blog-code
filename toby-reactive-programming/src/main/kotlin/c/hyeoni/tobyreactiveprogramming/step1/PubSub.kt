package c.hyeoni.tobyreactiveprogramming.step1

import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

fun main() {

    val numbers = listOf(1, 2, 3, 4, 5)

    val publisher = object : Publisher<Int> {

        override fun subscribe(subscriber: Subscriber<in Int>) {
            val iterator = numbers.iterator()

            subscriber.onSubscribe(

                object : Subscription {

                    override fun request(n: Long) {
                        runCatching {
                            var index = n
                            while (index-- > 0L) {
                                if (iterator.hasNext()) {
                                    subscriber.onNext(iterator.next())
                                } else {
                                    subscriber.onComplete()
                                    break
                                }
                            }
                        }.getOrElse { e -> subscriber.onError(e) }
                    }

                    override fun cancel() {
                        TODO("Not yet implemented")
                    }
                }
            )
        }
    }

    val subscriber = object : Subscriber<Int> {
        lateinit var subscription : Subscription

        override fun onSubscribe(subscription: Subscription) {
            println("onSubscribe")
            this.subscription = subscription
            this.subscription.request(1)
        }

        override fun onNext(item: Int) {
            println("onNext $item")
            this.subscription.request(1)
        }

        override fun onError(t: Throwable) {
            println("onError")
        }

        override fun onComplete() {
            println("onComplete")
        }
    }

    publisher.subscribe(subscriber)
}
