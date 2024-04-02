package c.hyeoni.aggregateroot.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.domain.AbstractAggregateRoot

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", length = 100, nullable = false)
    var name: String,

    @Column(name = "deleted", nullable = false)
    var deleted: Boolean
) : AbstractAggregateRoot<Member>()
