package c.hyeoni.tobyspringbootwithkotlin.section07

import c.hyeoni.tobyspringbootwithkotlin.section08.HelloService
import org.springframework.stereotype.Service

@Service
class SimpleHelloService : HelloService {

    override fun sayHello(name: String): String {
        return "Hello $name"
    }
}
