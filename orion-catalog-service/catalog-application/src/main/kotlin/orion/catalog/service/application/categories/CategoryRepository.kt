package orion.catalog.service.application.categories

import java.util.UUID

interface CategoryRepository {

    fun existsById(id: UUID): Boolean
}