package c.hyeoni.testcontainers.config

import c.hyeoni.testcontainers.TestcontainersApplication
import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@ActiveProfiles("testcontainers")
@TestConfiguration(proxyBeanMethods = false)
class ContainersConfig {

    @Bean
    @ServiceConnection
    fun mysqlContainer(): MySQLContainer<*> {
        return MySQLContainer(DockerImageName.parse("mysql:latest"))
            .withDatabaseName("test-example")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true)
    }
}

fun main(args: Array<String>) {
    fromApplication<TestcontainersApplication>()
        .with(ContainersConfig::class)
        .run(*args)
}
