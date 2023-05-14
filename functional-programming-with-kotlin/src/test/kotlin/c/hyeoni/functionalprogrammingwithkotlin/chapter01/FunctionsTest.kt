package c.hyeoni.functionalprogrammingwithkotlin.chapter01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FunctionsTest {

    @Test
    fun `동일한 입력에 동일한 출력을 반환한다`() {
        val result1 = sum(1, 2)
        val result2 = sum(1, 2)

        assertThat(result1).isEqualTo(result2)
    }

    @Test
    fun `함수의 실행 도중 함수 외부의 상태를 변경하지 않는다`() {
        val x = 1
        val y = 2
        val z = 3

        val result = sum(x, y)

        assertThat(result).isEqualTo(3)
        assertThat(z).isEqualTo(3)
    }
}
