package orion.catalog.service.application.products.dto

import java.util.UUID

data class ProductDto(
    val id: String,
    val name: String,
    val description: String?,
    val price: Double,
    val availableInStock: Int,
    val categories: Set<UUID>?
)
