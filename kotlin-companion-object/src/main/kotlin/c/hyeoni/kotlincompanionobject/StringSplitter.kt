package c.hyeoni.kotlincompanionobject

object StringSplitter {

    fun split(value: String, delimiter: String): List<String> {
        return value.split(delimiter)
    }
}
