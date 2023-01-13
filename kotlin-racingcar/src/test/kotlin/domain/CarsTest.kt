package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class CarsTest {

    @Test
    fun `자동차 목록 일급 컬렉션을 생성한다`() {
        val cars = listOf("pat", "mat")
            .map { CarName(it) }
            .map { Car(it) }

        val actual = Cars(cars)

        assertEquals(actual.value.size, 2)
    }

    @Test
    fun `자동차 목록 중 이름이 중복되는 경우 예외를 던진다`() {
        val cars = listOf("mat", "mat")
            .map { CarName(it) }
            .map { Car(it) }

        assertThrows<IllegalArgumentException> { Cars(cars) }
    }
}
