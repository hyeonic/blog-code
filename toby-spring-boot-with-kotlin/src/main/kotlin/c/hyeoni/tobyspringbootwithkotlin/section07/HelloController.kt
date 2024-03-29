package c.hyeoni.tobyspringbootwithkotlin.section07

import c.hyeoni.tobyspringbootwithkotlin.section08.HelloService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val helloService: HelloService
) {

    @GetMapping("/hello")
    fun hello(name: String?): String {
        requireNotNull(name)
        require(name.isNotBlank())

        return helloService.sayHello(name)
    }
}
