package c.hyeoni.transactionexceptionrollback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransactionExceptionRollbackApplication

fun main(args: Array<String>) {
    runApplication<TransactionExceptionRollbackApplication>(*args)
}
