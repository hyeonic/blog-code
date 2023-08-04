package c.hyeoni.reactiveprogrammingwithspring

import mu.KotlinLogging
import reactor.core.publisher.BufferOverflowStrategy
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

private val log = KotlinLogging.logger {}

fun main() {
    Flux.interval(Duration.ofMillis(1L))
            .doOnNext { log.info { "# emitted by original Flux: $it" } }
            .onBackpressureBuffer(
                    2,
                    { dropped -> log.info { "** Overflow & Dropped $dropped **" } },
                    BufferOverflowStrategy.DROP_LATEST
            )
            .doOnNext { log.info { "[ # emitted by Buffer: $it ]" } }
            .publishOn(Schedulers.parallel(), false, 1)
            .subscribe(
                    { data ->
                        Thread.sleep(1000L)
                        log.info { "# onNext: $data" }
                    },
                    { log.error { "# onNext: $it" } }
            )

    Thread.sleep(3000L)
}
