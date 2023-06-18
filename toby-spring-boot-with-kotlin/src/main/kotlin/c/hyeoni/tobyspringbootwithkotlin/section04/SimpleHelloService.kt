package c.hyeoni.tobyspringbootwithkotlin.section04

import org.springframework.stereotype.Service

@Service
class SimpleHelloService : HelloService {

    override fun sayHello(name: String): String {
        return "Hello $name"
    }
}
