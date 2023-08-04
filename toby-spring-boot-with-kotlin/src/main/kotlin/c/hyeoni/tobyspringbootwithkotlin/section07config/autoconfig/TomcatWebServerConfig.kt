package c.hyeoni.tobyspringbootwithkotlin.section07config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section07config.MyAutoConfiguration
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
