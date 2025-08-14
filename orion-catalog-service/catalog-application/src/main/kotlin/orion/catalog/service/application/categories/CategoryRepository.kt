package orion.catalog.service.application.categories

import orion.catalog.service.domain.category.Category
import java.util.UUID

interface CategoryRepository {

    fun existsById(id: UUID): Boolean

    fun getById(id: UUID): Category?
}