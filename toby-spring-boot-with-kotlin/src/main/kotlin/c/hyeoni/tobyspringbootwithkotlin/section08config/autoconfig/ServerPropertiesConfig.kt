package c.hyeoni.tobyspringbootwithkotlin.section08config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section06config.MyAutoConfiguration
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@MyAutoConfiguration
class ServerPropertiesConfig {

//    @Bean
    fun serverProperties(env: Environment): ServerProperties {
        return Binder.get(env)
            .bind("", ServerProperties::class.java)
            .get()
    }
}
