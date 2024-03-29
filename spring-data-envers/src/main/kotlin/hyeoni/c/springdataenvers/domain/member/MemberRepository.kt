package hyeoni.c.springdataenvers.domain.member

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>
//    , RevisionRepository<Member, Long, Long>

fun MemberRepository.getMemberById(id: Long): Member {
    return this.findById(id)
        .orElseThrow { NotFoundException() }
}
