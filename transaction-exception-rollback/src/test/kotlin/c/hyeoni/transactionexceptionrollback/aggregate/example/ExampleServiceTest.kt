package c.hyeoni.transactionexceptionrollback.aggregate.example

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExampleServiceTest @Autowired private constructor(
    private val exampleService: ExampleService
) {

    @Test
    fun exceptionCatch() {
        exampleService.exceptionCatch()
    }

    @Test
    fun exceptionCatchReturnNull() {
        exampleService.exceptionCatchReturnNull()
    }

    @Test
    fun errorCatchReturnNull() {
        exampleService.errorCatchReturnNull()
    }

    @Test
    fun getByIds() {
        exampleService.getByIds()
    }
}
