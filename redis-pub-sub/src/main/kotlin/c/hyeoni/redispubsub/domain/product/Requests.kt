package c.hyeoni.redispubsub.domain.product

data class ProductSaveRequest(
    val name: String?,
    val description: String?
) {

    companion object {

        fun ProductSaveRequest.toEntity(): Product {
            return Product(
                name = requireNotNull(this.name),
                description = requireNotNull(this.description)
            )
        }
    }
}

data class ProductUpdateRequest(
    val name: String?,
    val description: String?
)

data class ProductDefaultResponse(
    val id: Long,
    val name: String,
    val description: String,
    val versionNo: Long
) {
    companion object {

        fun Product.toDefaultResponse(): ProductDefaultResponse {
            return ProductDefaultResponse(
                id = this.id,
                name = this.name,
                description = this.description,
                versionNo = this.versionNo
            )
        }
    }
}
