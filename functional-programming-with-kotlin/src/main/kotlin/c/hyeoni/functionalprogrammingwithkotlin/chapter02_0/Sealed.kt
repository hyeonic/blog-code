package c.hyeoni.functionalprogrammingwithkotlin.chapter02_0

sealed class Result

data class Success<T>(val value: T) : Result()

data class Failure(val throwable: Throwable) : Result()

fun getOrThrow(result: Result): Any? {
    return when(result) {
        is Success<*> -> result.value
        is Failure -> result.throwable
    }
}
