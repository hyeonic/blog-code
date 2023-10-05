package c.hyeoni.tobyreactiveprogramming.step2

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

open class DelegateSubscriber(
    private val subscriber: Subscriber<in Int>
) : Subscriber<Int> {
    override fun onSubscribe(s: Subscription) {
        subscriber.onSubscribe(s)
    }

    override fun onNext(t: Int) {
        subscriber.onNext(t)
    }

    override fun onError(t: Throwable) {
        subscriber.onError(t)
    }

    override fun onComplete() {
        subscriber.onComplete()
    }
}
