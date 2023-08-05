package c.hyeoni.tobyspringbootwithkotlin.section07

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class WebServerConfiguration {

    @Bean
    fun customerWebServerFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
            .apply { port = 9090 }
    }
}
