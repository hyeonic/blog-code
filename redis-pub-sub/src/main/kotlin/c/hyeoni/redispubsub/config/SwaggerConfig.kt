package c.hyeoni.redispubsub.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(Info().title("Product Platform"))
            .also { log.info { "swagger url -> http://localhost:8080/swagger-ui/index.html" } }
    }

    companion object {
        val log = KotlinLogging.logger { }
    }
}
