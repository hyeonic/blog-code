package hyeoni.c.jpaauditing.domain

import hyeoni.c.jpaauditing.common.RequestContext
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun save(member: Member) {
        RequestContext.currentAuditor = member.name
        memberRepository.save(member)
    }
}
