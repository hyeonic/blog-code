package c.hyeoni.aggregateroot.domain

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class MemberEventListener {

    @EventListener
    fun onMemberSoftDeletedEvent(event: MemberSoftDeletedEvent) {
        println("id가 ${event.id}인 `member`가 삭제 되었습니다.")
    }
}
