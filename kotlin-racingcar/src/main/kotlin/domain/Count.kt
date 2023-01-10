package domain

import java.lang.NumberFormatException

class Count(
    val value: String
) {

    init {
        val parseValue = parseInt()
        validateNegativeNumber(parseValue)
    }

    private fun parseInt() = try {
        Integer.parseInt(value)
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("[예외] 시도 횟수는 숫자만 가능합니다")
    }

    private fun validateNegativeNumber(parsingValue: Int) {
        if (parsingValue < 0) {
            throw IllegalArgumentException("[예외] 시도 횟수는 0회 이상입니다.")
        }
    }
}
