package hyeoni.c.jpaauditing.domain

import hyeoni.c.jpaauditing.common.RequestContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun save(member: Member): Member {
        RequestContext.currentAuditor = member.name
        return memberRepository.save(member)
    }

    fun getOne(id: Long): Member {
        return memberRepository.findById(id)
            .orElseThrow { IllegalArgumentException() }
    }
}
