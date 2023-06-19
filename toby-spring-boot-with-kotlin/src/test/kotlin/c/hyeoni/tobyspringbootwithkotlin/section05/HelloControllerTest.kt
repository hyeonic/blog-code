package c.hyeoni.tobyspringbootwithkotlin.section05

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class HelloControllerTest {

    @Test
    fun helloController() {
        val helloController = HelloController { it }

        val result = helloController.hello("hyeonic")

        assertThat(result).isEqualTo("hyeonic")
    }

    @Test
    fun failHelloController() {
        val helloController = HelloController { it }

        assertThatThrownBy { helloController.hello("") }
            .isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy { helloController.hello(null) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
