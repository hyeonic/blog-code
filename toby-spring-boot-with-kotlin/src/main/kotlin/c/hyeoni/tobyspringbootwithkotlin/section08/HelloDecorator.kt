package c.hyeoni.tobyspringbootwithkotlin.section08

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class HelloDecorator(
    // decorator 입장에서 자신의 제외한 후보가 simpleHelloService로 하나이기 때문에 정상적으로 주입된다.
    private val helloService: HelloService
): HelloService {

    override fun sayHello(name: String): String {
       return "*${helloService.sayHello(name)}*"
    }
}
