package c.hyeoni.kotlincompanionobject.step2

const val DEFAULT_NAME = "hyeoni"

fun create(name: String): Person {
    return Person(name)
}

class Person(
    val name: String = DEFAULT_NAME
)
