package hyeoni.c.jpaauditing.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware")
class JpaConfig {

    @Bean
    fun customAuditorAware(): AuditorAware<String> {
       return CustomAuditorAware()
    }
}

class CustomAuditorAware: AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("default")
    }
}
