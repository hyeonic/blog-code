package c.hyeoni.tobyspringbootwithkotlin.section06

import c.hyeoni.tobyspringbootwithkotlin.section06config.MySpringBootApplication

@MySpringBootApplication
class Application

fun main(args: Array<String>) {
    MySpringApplication.run(Application::class.java, args)
}
