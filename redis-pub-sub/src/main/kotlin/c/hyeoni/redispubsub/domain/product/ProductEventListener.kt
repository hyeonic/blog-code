package c.hyeoni.redispubsub.domain.product

import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ProductEventListener(
    private val productPublisher: ProductPublisher
) {

    @TransactionalEventListener
    fun handleProductChangedEvent(event: ProductChangedEvent) {
        productPublisher.publish(PRODUCT_CHANGED_EVENT_CHANNEL_NAME, event.id)
    }

    companion object {
        const val PRODUCT_CHANGED_EVENT_CHANNEL_NAME = "product_changed_event_channel"
    }
}
