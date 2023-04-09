package c.hyeoni.kotlinresult

import org.springframework.stereotype.Repository

@Repository
class NameRepository {

    fun save(name: String): String {
        store[++count] = name
        return name
    }

    fun findById(id: Long): String {
        return store[id] ?: throw IllegalArgumentException()
    }

    companion object {
        var count = 0L
        val store = mutableMapOf<Long, String>()
    }
}
