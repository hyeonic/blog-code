package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class CarsTest {

    @Test
    fun `자동차 목록 일급 컬렉션을 생성한다`() {
        val carNames = listOf("pat", "mat")

        val actual = Cars(carNames, RandomMovable())

        assertEquals(actual.value.size, 2)
    }

    @Test
    fun `자동차 목록 중 이름이 중복되는 경우 예외를 던진다`() {
        val carNames = listOf("mat", "mat")

        assertThrows<IllegalArgumentException> { Cars(carNames, RandomMovable()) }
    }

    @Test
    fun `자동차 목록 중 우승자를 출력한다`() {
        val carNames = listOf("mat", "pat")
        val movable = { true }

        val cars = Cars(carNames, movable)
        cars.moveAll()

        val actual = cars.extractWinner()

        assertEquals(actual.size, 2)
    }
}
