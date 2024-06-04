package c.hyeoni.redispubsub.domain.product

import c.hyeoni.redispubsub.domain.product.ProductDefaultResponse.Companion.toDefaultResponse
import c.hyeoni.redispubsub.domain.product.ProductSaveRequest.Companion.toEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/products/{id}")
    fun getById(@PathVariable id: Long): ProductDefaultResponse {
        return productService.getById(id)
            .toDefaultResponse()
    }

    @PostMapping("/products")
    fun save(@RequestBody request: ProductSaveRequest): ProductDefaultResponse {
        return productService.save(request.toEntity())
            .toDefaultResponse()
    }

    @PutMapping("/products/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: ProductUpdateRequest
    ): ProductDefaultResponse {
        return productService.update(id, requireNotNull(request.name), requireNotNull(request.name))
            .toDefaultResponse()
    }
}
