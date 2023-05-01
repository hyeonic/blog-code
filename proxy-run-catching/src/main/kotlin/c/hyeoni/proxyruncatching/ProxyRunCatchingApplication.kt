package c.hyeoni.proxyruncatching

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProxyRunCatchingApplication

fun main(args: Array<String>) {
    runApplication<ProxyRunCatchingApplication>(*args)
}
