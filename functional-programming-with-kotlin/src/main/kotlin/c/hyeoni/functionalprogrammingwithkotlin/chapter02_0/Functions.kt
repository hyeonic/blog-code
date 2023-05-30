package c.hyeoni.functionalprogrammingwithkotlin.chapter02_0

fun getGrade(score: Int): String {
    return when(score) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        in 60..69 -> "D"
        else -> "F"
    }
}

fun main() {

    val numbers = mapOf(
        1 to "1",
        2 to "2",
        3 to "3"
    )

    for ((key, value) in numbers) {
        println("key: $key, value: $value")
    }

    val (key, value) = Pair("key", "value")

    val (_, _) = Pair("key", "value")
}
