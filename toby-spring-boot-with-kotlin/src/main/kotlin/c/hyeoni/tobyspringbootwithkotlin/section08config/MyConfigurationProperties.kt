package c.hyeoni.tobyspringbootwithkotlin.section08config

import org.springframework.stereotype.Component

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Component
annotation class MyConfigurationProperties(val prefix: String)
