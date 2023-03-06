package hyeoni.c.jpaauditing.common.config

import hyeoni.c.jpaauditing.common.RequestContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing
class JpaConfig {

    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware {
            Optional.of(RequestContext.currentAuditor)
        }
    }
}
