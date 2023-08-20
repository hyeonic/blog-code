package c.hyeoni.transactionexceptionrollback.aggregate.example

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExampleService(
    private val exampleRepository: ExampleRepository
) {

    @Transactional(readOnly = true)
    fun exceptionCatch() {
        exampleRepository.throwException()
    }

    @Transactional(readOnly = true)
    fun exceptionCatchReturnNull() {
        runCatching { exampleRepository.throwException() }
            .onSuccess { println("success") }
            .onFailure { println("failure") }
            .getOrNull()
    }

    @Transactional(readOnly = true)
    fun errorCatchReturnNull() {
        runCatching { exampleRepository.throwError() }
            .onSuccess { println("success") }
            .onFailure { println("failure") }
            .getOrNull()
    }

    @Transactional(readOnly = true)
    fun getByIds(): List<Long> {
        return runCatching { exampleRepository.findIds() }
            .onSuccess { println("success") }
            .onFailure { println("failure") }
            .getOrElse { emptyList() }
    }
}
