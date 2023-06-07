package c.hyeoni.functionalprogrammingwithkotlin.chapter03

import java.util.NoSuchElementException

fun main() {
//    val result = fibonacci(150)
//    println(result)

//    val result = power(3.0, 3)
//    println(result)

//    val result = factorial(5)
//    println(result)

//    val numbers = listOf(1, 3, 2, 8, 4)
//    val result = maximum(numbers)
//    println(result)

    val result = reverse("abcd")
    println(result)
}

fun fibonacci(n: Int): Int {
    return when(n) {
        0 -> 0
        1 -> 1
        else -> fibonacci(n - 1) + fibonacci(n - 2)
    }
}

fun power(x: Double, n: Int): Double {
    return when(n) {
        0 -> 1.0 // x의 0승은 1
        else -> x * power(x, n - 1)
    }
}

fun factorial(n: Long): Long {
    return when(n) {
        0L -> 1L
        else -> n * factorial(n - 1)
    }
}

fun List<Int>.head(): Int = this.first()

fun List<Int>.tail(): List<Int> = this.drop(1)

fun maximum(numbers: List<Int>): Int {
    return when {
        numbers.isEmpty() -> throw NoSuchElementException("List is empty.")
        numbers.size == 1 -> numbers.head()
        else -> {
            val head = numbers.head()
            val tail = numbers.tail()
            val maxNumber = maximum(tail)
            if (head > maxNumber) head else maxNumber
        }
    }
}

fun String.head(): Char = this.first()

fun String.tail(): String = this.drop(1)

fun reverse(str: String): String {
    return when {
        str.isEmpty() -> ""
        else -> reverse(str.tail()) + str.head()
    }
}
