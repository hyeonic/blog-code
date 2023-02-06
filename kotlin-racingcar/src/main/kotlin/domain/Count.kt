package domain

import java.lang.NumberFormatException

class Count(
    value: String,
) {

    var value: Int = parseInt(value)
        private set

    init {
        validateNegativeNumber()
    }

    private fun parseInt(count: String) = try {
        Integer.parseInt(count)
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("[예외] 시도 횟수는 숫자만 가능합니다")
    }

    private fun validateNegativeNumber() {
        if (value < 0) {
            throw IllegalArgumentException("[예외] 시도 횟수는 0회 이상입니다.")
        }
    }

    fun isZero() = value == 0

    fun next() = value--
}
