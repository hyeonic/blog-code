package c.hyeoni.tobyspringbootwithkotlin.section07config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section08config.ConditionalMyOnClass
import c.hyeoni.tobyspringbootwithkotlin.section08config.MyAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
class TomcatWebServerConfig {

    @Bean
    @ConditionalOnMissingBean
    fun tomcatServletWebServerFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }
}

class TomcatCondition : Condition {

    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.classLoader)
    }
}

