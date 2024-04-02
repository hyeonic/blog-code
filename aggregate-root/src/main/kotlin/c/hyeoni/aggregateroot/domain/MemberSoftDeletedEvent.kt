package c.hyeoni.aggregateroot.domain

import org.springframework.context.ApplicationEvent

data class MemberSoftDeletedEvent(val id: Long): ApplicationEvent(id)
