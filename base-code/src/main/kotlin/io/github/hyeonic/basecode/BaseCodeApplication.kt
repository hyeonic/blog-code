package io.github.hyeonic.basecode

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BaseCodeApplication

fun main(args: Array<String>) {
    runApplication<BaseCodeApplication>(*args)
}
