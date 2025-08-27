package orion.catalog.catalog_bff.application.products.dto

data class ProductWithCategoryDto(
    val name: String,
    val categories: Set<CategoryDto>
)

data class CategoryDto(
    val name: String,
    val description: String
)
