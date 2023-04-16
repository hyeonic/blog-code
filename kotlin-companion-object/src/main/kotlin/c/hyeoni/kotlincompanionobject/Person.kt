package c.hyeoni.kotlincompanionobject

class Person private constructor(
    val name: String
) {

    companion object Constant {
        const val DEFAULT_NAME = "hyeoni"

        fun create(name: String = DEFAULT_NAME): Person {
            return Person(name)
        }
    }
}
