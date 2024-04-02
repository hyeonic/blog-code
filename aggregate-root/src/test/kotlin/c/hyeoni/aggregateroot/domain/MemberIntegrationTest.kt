package c.hyeoni.aggregateroot.domain

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberIntegrationTest @Autowired constructor(
    private val memberService: MemberService
) {

    @Test
    fun some() {
        val savedMember = memberService.save(Member(name = "hyeonic"))

        memberService.delete(savedMember.id!!)
    }
}
