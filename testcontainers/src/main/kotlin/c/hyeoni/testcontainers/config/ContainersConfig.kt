package c.hyeoni.testcontainers.config

import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@Configuration(proxyBeanMethods = false)
class ContainersConfig {

    @Bean
    @Profile("testcontainers")
    @ServiceConnection
    fun mysqlContainer(): MySQLContainer<*> {
        return MySQLContainer(DockerImageName.parse("mysql:8.0.23"))
            .withDatabaseName("test-example")
            .withUsername("test")
            .withPassword("test")
    }
}
