package c.hyeoni.tobyspringbootwithkotlin.section07config

import c.hyeoni.tobyspringbootwithkotlin.section08config.MyOnClassCondition
import org.springframework.context.annotation.Conditional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Conditional(MyOnClassCondition::class)
annotation class ConditionalMyOnClass(val value: String)
