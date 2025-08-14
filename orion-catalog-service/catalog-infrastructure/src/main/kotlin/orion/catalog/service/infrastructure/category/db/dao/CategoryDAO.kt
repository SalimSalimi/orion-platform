package orion.catalog.service.infrastructure.category.db.dao

import org.springframework.data.jpa.repository.JpaRepository
import orion.catalog.service.infrastructure.category.db.entities.CategoryEntity
import java.util.UUID

interface CategoryDAO: JpaRepository<CategoryEntity, UUID> {
}