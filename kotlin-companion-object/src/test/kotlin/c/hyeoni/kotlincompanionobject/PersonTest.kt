package c.hyeoni.kotlincompanionobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    fun `default name을 조회한다 - 1`() {
        val defaultName = Person.DEFAULT_NAME

        assertThat(defaultName).isEqualTo("hyeoni")
    }

    @Test
    fun `default name을 조회한다 - 2`() {
//        val defaultName = Person.Companion.DEFAULT_NAME

//        assertThat(defaultName).isEqualTo("hyeoni")
    }


    @Test
    fun `default name을 조회한다 - 3`() {
        val defaultName = Person.Constant.DEFAULT_NAME

        assertThat(defaultName).isEqualTo("hyeoni")
    }

    @Test
    fun `factory method로 person을 생성한다`() {
        val person = Person.create()

        assertThat(person.name).isEqualTo("hyeoni")
    }
}
