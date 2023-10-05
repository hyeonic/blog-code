package hyeoni.c.springdataenvers.domain.member

import hyeoni.c.springdataenvers.config.JpaConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(JpaConfig::class)
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService
) {

    @Test
    fun `Member를 저장한다`() {
        val name = "hyeoni"

        val result = memberService.save(name)

        println(result)
    }
}
