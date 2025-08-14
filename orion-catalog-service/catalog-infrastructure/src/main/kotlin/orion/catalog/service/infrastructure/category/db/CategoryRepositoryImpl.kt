package orion.catalog.service.infrastructure.category.db

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import orion.catalog.service.application.categories.CategoryRepository
import orion.catalog.service.domain.category.Category
import orion.catalog.service.infrastructure.category.db.dao.CategoryDAO
import java.util.UUID

@Repository
class CategoryRepositoryImpl(
    private val categoryDAO: CategoryDAO
): CategoryRepository {
    override fun existsById(id: UUID): Boolean =
        categoryDAO.existsById(id)


    override fun getById(id: UUID): Category? {
        val categoryEntity = categoryDAO.findByIdOrNull(id)

        return if (categoryEntity == null) {
            null
        } else {
            with(categoryEntity) {
                Category(
                    id,
                    name,
                    description,
                    true
                )
            }
        }
    }
}