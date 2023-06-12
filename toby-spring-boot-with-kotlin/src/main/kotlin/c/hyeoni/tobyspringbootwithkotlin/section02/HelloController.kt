package c.hyeoni.tobyspringbootwithkotlin.section02

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(name: String): String {
        return "Hello $name";
    }
}
