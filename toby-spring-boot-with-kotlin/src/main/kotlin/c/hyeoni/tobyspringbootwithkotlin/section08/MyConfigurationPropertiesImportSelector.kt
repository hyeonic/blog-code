package c.hyeoni.tobyspringbootwithkotlin.section08

import c.hyeoni.tobyspringbootwithkotlin.section08config.EnableMyConfigurationProperties
import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyConfigurationPropertiesImportSelector : DeferredImportSelector {

    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        val attributes = importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties::class.java.name)
        val propertyClass = attributes?.getFirst("value") as Class<*>

        return arrayOf(requireNotNull(propertyClass.name))
    }
}
