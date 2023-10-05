package c.hyeoni.tobyspringbootwithkotlin.section08config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section06config.MyAutoConfiguration
import c.hyeoni.tobyspringbootwithkotlin.section08config.MyConfigurationProperties
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.core.env.Environment

@MyAutoConfiguration
class PropertyPostProcessorConfig {

    @Bean
    fun propertyPostProcessor(env: Environment): BeanPostProcessor {
        return object : BeanPostProcessor {

            override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
                return AnnotationUtils.findAnnotation(bean.javaClass, MyConfigurationProperties::class.java)
                        ?.let {
                            val attributes = AnnotationUtils.getAnnotationAttributes(it)
                            val prefix = attributes["prefix"].toString()

                            Binder.get(env).bindOrCreate(prefix, bean.javaClass)
                        }
                        ?: bean
            }
        }
    }
}
