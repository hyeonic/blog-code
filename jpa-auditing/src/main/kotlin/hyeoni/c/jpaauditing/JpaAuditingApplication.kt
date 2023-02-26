package hyeoni.c.jpaauditing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JpaAuditingApplication

fun main(args: Array<String>) {
    runApplication<JpaAuditingApplication>(*args)
}
