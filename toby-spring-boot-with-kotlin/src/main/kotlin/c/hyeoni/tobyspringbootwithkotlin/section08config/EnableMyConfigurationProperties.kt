package c.hyeoni.tobyspringbootwithkotlin.section08config

import c.hyeoni.tobyspringbootwithkotlin.section08.MyConfigurationPropertiesImportSelector
import org.springframework.context.annotation.Import
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Import(MyConfigurationPropertiesImportSelector::class)
annotation class EnableMyConfigurationProperties(val value: KClass<*>)
