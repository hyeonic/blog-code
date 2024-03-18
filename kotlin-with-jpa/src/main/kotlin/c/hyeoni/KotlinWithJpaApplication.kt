package c.hyeoni

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinWithJpaApplication

fun main(args: Array<String>) {
    runApplication<KotlinWithJpaApplication>(*args)
}
