package c.hyeoni.kotlincompanionobject.step1

class Person private constructor(
    val name: String = DEFAULT_NAME
) {

    companion object Constant {
        const val DEFAULT_NAME = "hyeoni"

        fun create(name: String = DEFAULT_NAME): Person {
            return Person(name)
        }
    }
}
