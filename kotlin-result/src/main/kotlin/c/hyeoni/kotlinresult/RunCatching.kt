package c.hyeoni.kotlinresult

import org.springframework.stereotype.Service

@Service
class NameService(
    private val repository: NameRepository
) {

    fun getById(id: Long): String {
        return runCatching { repository.findById(id) }
            .getOrThrow()
    }
}
