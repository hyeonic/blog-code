package c.hyeoni.testcontainers.aggregate.member.domain

import c.hyeoni.testcontainers.config.ContainersConfig
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ContainersConfig::class)
@ActiveProfiles("testcontainers")
class MemberRepositoryIntegrationTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun saveTest() {
        val member = Member(name = "hyeonic")

        assertThatCode { memberRepository.save(member) }.doesNotThrowAnyException()
    }
}
