import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
    val input = scanner.nextLine()
    val carNames = input.split(",")

    println(carNames)
}
