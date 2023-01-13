package domain

class RandomMovable : Movable {

    companion object {
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 9
        private const val STANDARD_NUMBER = 4
    }

    override fun isMove(): Boolean {
        return (MIN_NUMBER..MAX_NUMBER).random() >= STANDARD_NUMBER
    }
}
