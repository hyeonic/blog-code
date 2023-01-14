import domain.*
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
    val input = scanner.nextLine()
    val carNames = input.split(",")

    val cars = Cars(carNames, RandomMovable())

    println("시도할 회수는 몇회인가요?")
    val count = Count(scanner.nextLine())

    println("\n실행 결과")
    while (!count.isZero()) {
        cars.moveAll()
        printCar(cars)
        count.next()
    }

    val winners = cars.extractWinner()
        .map(Car::carName)
        .map(CarName::value)
        .joinToString(", ")
    println("${winners}가 최종 우승했습니다.")
}

private fun printCar(cars: Cars) {
    for (car in cars.value) {
        val carName = car.carName
        val position = "-".repeat(car.position)
        println("${carName.value}: ${position}")
    }

    println()
}
