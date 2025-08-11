package orion.catalog.service.application.products.dto

data class ProductDto(
    val id: String,
    val name: String,
    val description: String?,
    val price: Double,
    val availableInStock: Int,
)
