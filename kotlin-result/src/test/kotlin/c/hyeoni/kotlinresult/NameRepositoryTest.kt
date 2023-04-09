package c.hyeoni.kotlinresult

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class NameRepositoryTest {

    @Test
    fun `존재하지 않는 id 조회 시 예외를 던진다`() {
        val nameRepository = NameRepository()

        assertThatThrownBy { nameRepository.findById(0L) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
