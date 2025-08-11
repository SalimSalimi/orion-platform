package orion.catalog.service.infrastructure.product.db.jpa

import org.springframework.data.jpa.repository.JpaRepository
import orion.catalog.service.infrastructure.product.db.entities.ProductEntity
import java.util.UUID

interface ProductJpaRepository: JpaRepository<ProductEntity, UUID>