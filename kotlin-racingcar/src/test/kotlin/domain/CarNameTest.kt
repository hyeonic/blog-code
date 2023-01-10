package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CarNameTest {

    @Test
    fun `자동차 이름을 생성한다`() {
        val carName = "mat"

        assertDoesNotThrow<CarName>() { CarName(carName) }
    }

    @Test
    fun `자동차의 이름이 6자 이상이면 예외를 던진다`() {
        val carName = "patandmat"

        assertThrows<IllegalArgumentException> { CarName(carName) }
    }

    @Test
    fun `자동차의 이름이 공백인 경우 예외를 던진다`() {
        val carName = " "

        assertThrows<IllegalArgumentException> { CarName(carName) }
    }

    @Test
    fun `자동차의 이름이 비어있는 경우 예외를 던진다`() {
        var carName = ""

        assertThrows<IllegalArgumentException> { CarName(carName) }
    }
}
