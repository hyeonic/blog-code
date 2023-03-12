package hyeoni.c.springdataenvers.domain.member

import org.hibernate.envers.Audited
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Audited
@Entity
class Member(
    name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column
    var name: String = name
        protected set

    override fun toString(): String {
        return "Member(id=$id, name='$name')"
    }
}
