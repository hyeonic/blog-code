package c.hyeoni.redispubsub.domain.product

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    @Transactional
    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    @Transactional
    fun update(id: Long, name: String, description: String): Product {
        return getById(id).apply {
            this.name = name
            this.description = description
        }.also {
            it.notifyChanged()
            productRepository.save(it)
        }
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): Product {
        return productRepository.findById(id)
            .orElseThrow()
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Product> {
        return productRepository.findAll()
    }
}
