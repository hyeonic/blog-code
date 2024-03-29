package c.hyeoni.tobyspringbootwithkotlin.section07config

import c.hyeoni.tobyspringbootwithkotlin.section08config.EnableMyAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Configuration
@ComponentScan
@EnableMyAutoConfiguration
annotation class MySpringBootApplication
