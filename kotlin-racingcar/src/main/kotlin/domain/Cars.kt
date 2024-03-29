package domain

class Cars(
    value: List<String>,
    private val movable: Movable
) {

    val value: List<Car> = value.toCar()

    private fun List<String>.toCar() = map(::CarName).map(::Car)

    init {
        validateDuplicatedCarName()
    }

    private fun validateDuplicatedCarName() {
        if (value.size != toNoneDuplicatedCarNames().size) {
            throw IllegalArgumentException("[예외] 자동차의 이름은 중복될 수 없습니다.")
        }
    }

    private fun toNoneDuplicatedCarNames() = value.map(Car::carName)
        .map(CarName::value)
        .toSet()

    fun moveAll() {
        for (car in value) {
            car.moveOrStop(movable)
        }
    }

    fun extractWinner(): List<Car> {
        val maxCarPosition = value.maxOf(Car::position)

        return value.filter { maxCarPosition == it.position }
    }
}
