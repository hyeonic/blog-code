package c.hyeoni.redispubsub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisPubSubApplication

fun main(args: Array<String>) {
    runApplication<RedisPubSubApplication>(*args)
}
