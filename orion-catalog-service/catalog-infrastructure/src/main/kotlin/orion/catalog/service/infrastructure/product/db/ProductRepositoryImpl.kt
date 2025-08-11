package orion.catalog.service.infrastructure.product.db

import org.springframework.stereotype.Repository
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.domain.product.Product
import orion.catalog.service.infrastructure.product.db.entities.ProductEntity
import orion.catalog.service.infrastructure.product.db.jpa.ProductJpaRepository
import java.util.UUID

@Repository
class ProductRepositoryImpl(
    private val jpaRepository: ProductJpaRepository
): ProductRepository {

    override fun create(product: Product): UUID {
        val productEntity = ProductEntity(
            id = product.uuid,
            name = product.name,
            description = product.description,
            price = product.price,
            stock = product.availableStock,
        )

        val savedEntity = this.jpaRepository.save<ProductEntity>(productEntity)

        return savedEntity.id
    }

    override fun getById(id: UUID): Product? {
        val productEntity = jpaRepository.findById(id)
        if (productEntity.isPresent) {
            with(productEntity.get()) {
                return Product(
                    id,
                    name,
                    description,
                    price,
                    availableStock = stock,
                    categoryId = emptySet()
                )
            }
        } else {
            return null
        }
    }

}