package c.hyeoni.generic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GenericApplication

fun main(args: Array<String>) {
    runApplication<GenericApplication>(*args)
}
