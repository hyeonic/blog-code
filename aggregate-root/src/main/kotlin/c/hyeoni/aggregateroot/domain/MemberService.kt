package c.hyeoni.aggregateroot.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun save(member: Member): Member {
        return memberRepository.save(member)
    }

    @Transactional
    fun delete(id: Long) {
        val foundMember = memberRepository.findById(id).orElseThrow()

        foundMember.delete()
        memberRepository.save(foundMember)
    }
}
