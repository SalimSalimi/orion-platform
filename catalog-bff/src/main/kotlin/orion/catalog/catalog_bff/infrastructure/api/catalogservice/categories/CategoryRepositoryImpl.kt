package orion.catalog.catalog_bff.infrastructure.api.catalogservice.categories

import org.springframework.stereotype.Repository
import orion.catalog.catalog_bff.application.categories.repository.CategoryRepository
import orion.catalog.catalog_bff.domain.category.Category

@Repository
class CategoryRepositoryImpl(
    private val categoryClient: CategoryCatalogServiceClient
): CategoryRepository {
    override fun getById(id: String): Category? {
        val categoryDto = categoryClient.getById(id)
        return if (categoryDto == null) {
            null
        } else {
            return with(categoryDto) {
                Category(id, name, description ?: "")
            }
        }
    }
}