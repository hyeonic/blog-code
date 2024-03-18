package c.hyeoni.domain

import org.springframework.data.jpa.repository.JpaRepository

interface KotlinMemberRepository : JpaRepository<KotlinMember, Long>
