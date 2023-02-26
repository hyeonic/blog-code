package hyeoni.c.jpaauditing.common

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    @Column(nullable = false)
    var lastModifiedDate: LocalDateTime = LocalDateTime.now()
        protected set

    @CreatedBy
    @Column(nullable = false, updatable = false)
    var createdBy: String = DEFAULT_MEMBER_NAME
        protected set

    @LastModifiedBy
    @Column(nullable = false)
    var lastModifiedBy: String = DEFAULT_MEMBER_NAME
        protected set

    companion object {
        const val DEFAULT_MEMBER_NAME = "default member"
    }
}
