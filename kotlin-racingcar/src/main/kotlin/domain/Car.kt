package domain

class Car(
    val carName: CarName,
    position: Int = 0,
) {

    var position: Int = 0
        private set

    fun moveOrStop(movable: Movable) {
        if (movable.isMove()) {
            position++
        }
    }
}
