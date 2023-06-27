package c.hyeoni.kotlinfirstclassfunctioncompile.utils

fun Int.convertString(transform: (Int) -> String): String {
    return transform(this)
}
