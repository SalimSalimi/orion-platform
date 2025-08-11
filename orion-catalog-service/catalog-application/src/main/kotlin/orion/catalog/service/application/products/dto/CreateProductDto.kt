package orion.catalog.service.application.products.dto

import java.util.UUID

data class CreateProductDto(
    val name: String,
    val description: String?,
    val price: Double,
    val stock: Int,
    val categoryIds: List<UUID>
)
