package c.hyeoni.redispubsub.infrastructure

import c.hyeoni.redispubsub.domain.product.ProductPublisher
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisProductPublisher(
    private val productRedisTemplate: RedisTemplate<String, String>
) : ProductPublisher {

    override fun publish(channelName: String, id: Long) {
        productRedisTemplate.convertAndSend(channelName, id.toString())
    }
}
