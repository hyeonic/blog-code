package c.hyeoni.reactiveprogrammingwithspring

import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

private val log = KotlinLogging.logger {}

fun main() {
    Flux.interval(Duration.ofMillis(1L))
            .onBackpressureDrop { dropped -> log.info { "# dropped: $dropped" } }
            .publishOn(Schedulers.parallel())
            .subscribe(
                    { data ->
                        Thread.sleep(5L)
                        log.info { "# onNext: $data" }
                    },
                    { log.error { "# onNext: $it" } }
            )

    Thread.sleep(2000L)
}
