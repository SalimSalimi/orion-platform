package orion.catalog.catalog_bff.infrastructure.api.catalogservice.categories.dto

data class CategoryCatalogServiceDTO(
    val id: String,
    var name: String,
    var description: String? = null,
)