package c.hyeoni.tobyspringbootwithkotlin.section08config

import org.springframework.boot.context.annotation.ImportCandidates
import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyAutoConfigImportSelector(
        private val classLoader: ClassLoader
) : DeferredImportSelector {

    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        return ImportCandidates.load(MyAutoConfiguration::class.java, classLoader)
                .iterator()
                .asSequence()
                .toList()
                .toTypedArray()
    }
}
