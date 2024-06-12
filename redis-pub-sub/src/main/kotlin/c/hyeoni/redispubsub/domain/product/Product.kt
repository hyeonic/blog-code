package c.hyeoni.redispubsub.domain.product

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Version
import org.springframework.context.ApplicationEvent
import org.springframework.data.domain.AbstractAggregateRoot

@Entity
class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(unique = true, nullable = false)
    var name: String,

    @Column(unique = true, nullable = false)
    var description: String
) : AbstractAggregateRoot<Product>() {

    @Version
    var versionNo: Long = 0L

    fun notifyChanged() {
        if (id != 0L) {
            this.registerEvent(ProductChangedEvent(this.id))
        }
    }
}

data class ProductChangedEvent(val id: Long): ApplicationEvent(id)
