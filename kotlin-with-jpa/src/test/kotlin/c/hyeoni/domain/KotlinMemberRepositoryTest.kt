package c.hyeoni.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class KotlinMemberRepositoryTest
@Autowired constructor(
    private val memberRepository: KotlinMemberRepository
) {

    @Test
    fun save() {
        val savedMember = memberRepository.save(KotlinMember(name = "hyeonic"))

        assertThat(savedMember.name).isEqualTo("hyeonic")
    }
}
