package c.hyeoni.redispubsub.config

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(
        @Value("\${spring.data.redis.host}") host: String,
        @Value("\${spring.data.redis.port}") port: Int
    ): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port).also { log.info { "redis access -> redis-cli -h $host -p $port" } }
    }

    @Bean
    fun productRedisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, String> {
        return RedisTemplate<String, String>()
            .apply {
                this.connectionFactory = redisConnectionFactory

                this.keySerializer = StringRedisSerializer()
                this.valueSerializer = StringRedisSerializer()
            }
    }

    companion object {
        val log = KotlinLogging.logger { }
    }
}
