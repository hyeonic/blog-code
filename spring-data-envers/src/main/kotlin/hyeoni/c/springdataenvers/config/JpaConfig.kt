package hyeoni.c.springdataenvers.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(
    basePackages = ["hyeoni.c.springdataenvers.domain"],
    repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class
)
@Configuration
class JpaConfig
