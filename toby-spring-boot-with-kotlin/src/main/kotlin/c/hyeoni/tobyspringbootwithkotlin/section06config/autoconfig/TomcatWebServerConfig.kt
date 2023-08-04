package c.hyeoni.tobyspringbootwithkotlin.section06config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section06config.MyAutoConfiguration
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean

@MyAutoConfiguration
class TomcatWebServerConfig {

    @Bean
    fun servletWebServerFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }
}
