package c.hyeoni.testcontainers.aggregate.member.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Member(
    @Id
    var id: Long = 0L,
    var name: String
)
