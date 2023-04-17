package c.hyeoni.kotlincompanionobject.step3

class Person private constructor(
    private val name: String = DEFAULT_NAME
) {

    companion object {
        val DEFAULT_NAME = "hyeoni"

        fun create(name: String = DEFAULT_NAME): Person {
            println(DEFAULT_NAME)
            return Person(name)
        }
    }
}
