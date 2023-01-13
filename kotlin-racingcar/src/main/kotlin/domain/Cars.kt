package domain

class Cars(
    val value: List<Car>,
) {

    init {
        validateDuplicatedCarName()
    }

    private fun validateDuplicatedCarName() {
        if (value.size != toNoneDuplicatedCarNames().size) {
            throw IllegalArgumentException("[예외] 자동차의 이름은 중복될 수 없습니다.")
        }
    }

    private fun toNoneDuplicatedCarNames() = value
        .map { it.carName }
        .map { it.value }
        .toSet()
}
