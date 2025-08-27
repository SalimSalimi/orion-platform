package orion.catalog.catalog_bff.infrastructure.api.catalogservice.dto

data class ProductDtoCatalogService(
    val id: String,
    val name: String,
    val description: String?,
    val price: Double,
    val availableInStock: Int,
    val categories: Set<String>
)
