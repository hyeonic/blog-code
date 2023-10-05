package c.hyeoni.tobyspringbootwithkotlin.section08

import c.hyeoni.tobyspringbootwithkotlin.section08config.MySpringBootApplication
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@MySpringBootApplication
class Application {

    @Bean
    fun applicationRunner(env: Environment): ApplicationRunner {
        return ApplicationRunner { args ->
            val name = env.getProperty("my.name")
            println("my.name: $name")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
