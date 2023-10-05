package c.hyeoni.tobyreactiveprogramming.step2

import mu.KotlinLogging
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class LogSubscriber : Subscriber<Int> {

    override fun onSubscribe(subscription: Subscription) {
        log.debug("onSubscribe")
        subscription.request(Long.MAX_VALUE)
    }

    override fun onNext(item: Int) {
        log.debug("onNext $item")
    }

    override fun onError(t: Throwable) {
        log.debug("onError")
    }

    override fun onComplete() {
        log.debug("onComplete")
    }

    companion object {
        val log = KotlinLogging.logger { }
    }
}
