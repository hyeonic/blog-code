package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CountTest {

    @Test
    fun `시도 횟수를 생성한다`() {
        val count = "10"

        assertDoesNotThrow<Count> { Count(count) }
    }

    @Test
    fun `시도 횟수가 숫자가 아닌 경우 예외를 던진다`() {
        val count = "mat"

        assertThrows<IllegalArgumentException> { Count(count) }
    }

    @Test
    fun `시도 횟수가 음수인 경우 예외를 던진다`() {
        val count = "-1"

        assertThrows<IllegalArgumentException> { Count(count) }
    }
}
