package hyeoni.c.springdataenvers.domain.post

import hyeoni.c.springdataenvers.domain.member.Member
import org.hibernate.envers.Audited
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Audited
@Entity
class Post(
    content: String,
    member: Member
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column
    var content: String = content
        protected set

    @Column
    var views: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = member
        protected set

    fun updateContent(content: String) {
        this.content = content
    }

    override fun toString(): String {
        return "Post(id=$id, content='$content', views=$views, member=$member)"
    }
}
