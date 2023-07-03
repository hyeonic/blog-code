package c.hyeoni.tobyspringbootwithkotlin.section07

import c.hyeoni.tobyspringbootwithkotlin.section07config.MySpringBootApplication

@MySpringBootApplication
class Application

fun main(args: Array<String>) {
    MySpringApplication.run(Application::class.java, args)
}
