package c.hyeoni.proxyruncatching.domain

import c.hyeoni.proxyruncatching.common.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Member(
    @Column
    var name: String
) : BaseEntity()
