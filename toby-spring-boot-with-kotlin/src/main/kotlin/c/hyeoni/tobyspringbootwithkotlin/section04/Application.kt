package c.hyeoni.tobyspringbootwithkotlin.section04

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
class Application

fun main(args: Array<String>) {
    MySpringApplication.run(Application::class.java, args)
}
