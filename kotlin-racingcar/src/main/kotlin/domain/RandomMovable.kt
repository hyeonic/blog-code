package domain

class RandomMovable : Movable {

    override fun isMove(): Boolean {
        return (MIN_NUMBER..MAX_NUMBER).random() >= STANDARD_NUMBER
    }

    companion object {
        const val MIN_NUMBER = 1
        const val MAX_NUMBER = 9
        const val STANDARD_NUMBER = 4
    }
}
