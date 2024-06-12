package c.hyeoni.redispubsub.domain.product

interface ProductPublisher {

    fun publish(channelName: String, id: Long)
}
