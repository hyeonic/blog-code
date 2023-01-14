import domain.*
import java.lang.RuntimeException
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
    val cars = retryInputCarNames(scanner)

    println("시도할 회수는 몇회인가요?")
    val count = retryInputCount(scanner)

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

private fun retryInputCarNames(scanner: Scanner): Cars = try {
    val input = scanner.nextLine()
    val carNames = input.split(",")
    Cars(carNames, RandomMovable())
} catch (e: RuntimeException) {
    println(e.message)
    retryInputCarNames(scanner)
}

private fun retryInputCount(scanner: Scanner): Count = try {
    Count(scanner.nextLine())
} catch (e: RuntimeException) {
    println(e.message)
    retryInputCount(scanner)
}
