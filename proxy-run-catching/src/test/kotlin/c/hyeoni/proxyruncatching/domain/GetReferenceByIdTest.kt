package c.hyeoni.proxyruncatching.domain

import org.hibernate.Hibernate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@SpringBootTest
class GetReferenceByIdTest @Autowired constructor(
    private val repository: MemberRepository
) {

    @Test
    @Transactional
    fun `getReferenceById를 활용한 뒤 proxy 객체를 조회하고 id에 접근한다`() {
        val result = runCatching {
            repository.getReferenceById(1L).also { it.id }
        }

        println(result)
    }

    @Test
    @Transactional
    fun `getReferenceById를 활용한 뒤 proxy 객체를 조회하고 name에 접근한다`() {
        val result = runCatching {
            repository.getReferenceById(1L).name
        }

        println(result)
    }


    @Test
    @Transactional
    fun `Hibernate의 isInitialized를 활용하여 초기화 여부를 확인한다`() {
        val result = runCatching {
            repository.getReferenceById(1L)
                .also { proxyEntity -> Hibernate.unproxy(proxyEntity) }
        }

        println(result)
    }
}
