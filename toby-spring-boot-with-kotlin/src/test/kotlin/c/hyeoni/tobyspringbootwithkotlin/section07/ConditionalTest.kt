package c.hyeoni.tobyspringbootwithkotlin.section07

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.AnnotatedTypeMetadata

class ConditionalTest {

    @Test
    fun conditional() {
        // true
        ApplicationContextRunner().withUserConfiguration(Config1::class.java)
            .run { context ->
                assertThat(context).hasSingleBean(MyBean::class.java)
                assertThat(context).hasSingleBean(Config1::class.java)
            }

        // false
        ApplicationContextRunner().withUserConfiguration(Config2::class.java)
            .run { context ->
                assertThat(context).doesNotHaveBean(MyBean::class.java)
                assertThat(context).doesNotHaveBean(Config2::class.java)
            }
    }

    @Configuration
    @BooleanConditional(true)
    class Config1 {

        @Bean
        fun myBean(): MyBean {
            return MyBean()
        }
    }

    @Configuration
    @BooleanConditional(false)
    class Config2 {

        @Bean
        fun myBean(): MyBean {
            return MyBean()
        }
    }

    class MyBean

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    @Conditional(TrueCondition::class)
    annotation class TrueConditional

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    @Conditional(FalseCondition::class)
    annotation class FalseConditional

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    @Conditional(BooleanCondition::class)
    annotation class BooleanConditional(val value: Boolean)

    class TrueCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return true
        }
    }

    class FalseCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return false
        }
    }

    class BooleanCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            val annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional::class.java.name)
            return annotationAttributes?.get("value").toString().toBoolean()
        }
    }
}
