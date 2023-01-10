package domain

class RandomMovable : Movable {

    override fun isMove(): Boolean {
        val number = (1..9).random()
        return number >= 4
    }
}
