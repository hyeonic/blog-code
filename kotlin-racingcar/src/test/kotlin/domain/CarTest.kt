package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class CarTest {

    @Test
    fun `자동차를 생성한다`() {
        val carName = CarName("mat")

        val actual = Car(carName)

        assertAll({
            assertEquals(actual.carName, carName)
            assertEquals(actual.position, 0)
        })
    }

    @Test
    fun `움직일 수 있는 조건에 만족하면 자동차는 전진한다`() {
        val carName = CarName("mat")
        val car = Car(carName)
        val movable = Movable { true }

        car.moveOrStop(movable)
        car.moveOrStop(movable)
        car.moveOrStop(movable)

        assertEquals(car.position, 3)
    }

    @Test
    fun `움직일 수 없는 조건인 경우 자동차의 위치를 유지한다`() {
        val carName = CarName("mat")
        val car = Car(carName)
        val movable = Movable { false }

        car.moveOrStop(movable)
        car.moveOrStop(movable)
        car.moveOrStop(movable)

        assertEquals(car.position, 0)
    }
}
