package hyeoni.c.jpaauditing.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService
) {

    @Test
    fun `member를 저장한다`() {
        val name = "hyeoni"
        val savedMember = memberService.save(Member(name))

        val result = memberService.getOne(savedMember.id!!)

        println(result)
    }

    @Test
    fun `member를 저장한다 createdBy와 lastModifiedBy는 member의 name과 같다`() {
        val name = "hyeoni"
        val savedMember = memberService.save(Member(name))

        val result = memberService.getOne(savedMember.id!!)

        assertAll(
            { assertThat(result.createdBy).isEqualTo(name) },
            { assertThat(result.lastModifiedBy).isEqualTo(name) }
        )
    }
}
