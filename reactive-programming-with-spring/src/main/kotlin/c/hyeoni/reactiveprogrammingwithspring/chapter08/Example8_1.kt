package c.hyeoni.reactiveprogrammingwithspring

import mu.KotlinLogging
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

private val log = KotlinLogging.logger {}

fun main() {
    Flux.range(1, 5)
            .doOnRequest { log.info { "# doOnRequest: $it" } }
            .subscribe(Subscriber())
}

class Subscriber : BaseSubscriber<Int>() {
    override fun hookOnSubscribe(subscription: Subscription) {
        request(1)
    }

    override fun hookOnNext(value: Int) {
        Thread.sleep(2000L)
        log.info { "# hookOnNext: $value" }
        request(1)
    }
}


