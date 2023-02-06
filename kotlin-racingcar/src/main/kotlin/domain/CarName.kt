package domain

class CarName(
    val value: String,
) {

    init {
        require(value.trim().isNotEmpty()) { "[예외] 자동차의 이름은 공백일 수 없습니다." }
        require(value.length < 6) { "[예외] 자동차의 이름은 5자 이하입니다." }
    }
}
