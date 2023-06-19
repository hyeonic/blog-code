package c.hyeoni.tobyspringbootwithkotlin.section05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloServiceTest {

    @Test
    fun simpleHelloServiceTest() {
        val helloService = SimpleHelloService()

        val result = helloService.sayHello("hyeonic")

        assertThat(result).isEqualTo("Hello hyeonic")
    }

    @Test
    fun helloDecorator() {
        val decorator = HelloDecorator { it }

        val result = decorator.sayHello("hyeonic")

        assertThat(result).isEqualTo("*hyeonic*")
    }
}

