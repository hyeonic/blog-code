package c.hyeoni.tobyspringbootwithkotlin.section08config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section08config.ConditionalMyOnClass
import c.hyeoni.tobyspringbootwithkotlin.section08config.EnableMyConfigurationProperties
import c.hyeoni.tobyspringbootwithkotlin.section08config.MyAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(ServerProperties::class)
class TomcatWebServerConfig {

    @Bean
    @ConditionalOnMissingBean
    fun tomcatServletWebServerFactory(properties: ServerProperties): ServletWebServerFactory {
        val serverFactory = TomcatServletWebServerFactory()

        serverFactory.contextPath = properties.contextPath
        serverFactory.port = properties.port

        return serverFactory
    }
}
