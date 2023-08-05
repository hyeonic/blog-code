package c.hyeoni.tobyspringbootwithkotlin.section07config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section06config.MyAutoConfiguration
import c.hyeoni.tobyspringbootwithkotlin.section07config.ConditionalMyOnClass
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils

@MyAutoConfiguration
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
class JettyWebServerConfig {

    @Bean
    fun jettyServletWebServerFactory(): ServletWebServerFactory {
        return JettyServletWebServerFactory()
    }
}

class JettyCondition: Condition {

    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return ClassUtils.isPresent("org.eclipse.jetty.server.Server", context.classLoader)
    }
}
