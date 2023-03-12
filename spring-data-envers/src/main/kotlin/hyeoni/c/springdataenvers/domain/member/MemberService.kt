package hyeoni.c.springdataenvers.domain.member

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun save(name: String): Member {
        return Member(name)
    }
}
