package domain

class CarName(
    val value: String,
) {

    init {
        validateCarNameEmpty()
        validateCarNameLength()
    }

    private fun validateCarNameEmpty() {
        if (value.trim().isEmpty()) {
            throw IllegalArgumentException("[예외] 자동차의 이름은 공백일 수 없습니다.")
        }
    }

    private fun validateCarNameLength() {
        if (value.length >= 6) {
            throw IllegalArgumentException("[예외] 자동차의 이름은 5자 이하입니다.");
        }
    }
}
