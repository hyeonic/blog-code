package hyeoni.c.jpaauditing.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
}
