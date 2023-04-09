package c.hyeoni.kotlinresult

import org.springframework.stereotype.Service

@Service
class NameService(
    private val repository: NameRepository
) {

    fun getById(id: Long): Result<String> {
        return runCatching {
            repository.findById(id)
        }
    }
}
