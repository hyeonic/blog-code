package c.hyeoni.transactionexceptionrollback.aggregate.example

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ExampleRepository {

    @Transactional(readOnly = true)
    fun throwException() {
        throw IllegalArgumentException()
    }

    @Transactional(readOnly = true)
    fun throwError() {
        throw Error()
    }

    @Transactional(readOnly = true)
    fun findIds(): List<Long> {
        throw IllegalArgumentException()
    }
}
