package c.hyeoni.tobyspringbootwithkotlin.section07config

import c.hyeoni.tobyspringbootwithkotlin.section08config.MyAutoConfigImportSelector
import org.springframework.context.annotation.Import

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Import(MyAutoConfigImportSelector::class)
annotation class EnableMyAutoConfiguration
