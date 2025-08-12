package orion.catalog.service.infrastructure.product.db

import org.springframework.stereotype.Repository
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.domain.product.Product
import orion.catalog.service.infrastructure.product.db.entities.ProductEntity
import orion.catalog.service.infrastructure.product.db.jpa.ProductJpaRepository
import orion.catalog.service.infrastructure.product.db.mapper.ProductEntityMapper
import java.util.UUID

@Repository
class ProductRepositoryImpl(
    private val jpaRepository: ProductJpaRepository,
    private val productEntityMapper: ProductEntityMapper,
): ProductRepository {

    override fun create(product: Product): UUID {
        val productEntity = productEntityMapper.mapToEntity(product)
        val savedEntity = this.jpaRepository.save<ProductEntity>(productEntity)
        return savedEntity.id
    }

    override fun getById(id: UUID): Product? {
        val productEntity = jpaRepository.findById(id)
        if (productEntity.isPresent) {
           return productEntityMapper.mapFromEntity(productEntity.get())
        } else {
            return null
        }
    }

    override fun getAll(): Set<Product> {
        val productsEntity = jpaRepository.findAll()
        if (productsEntity.isNotEmpty()) {
            return productsEntity.map { productsEntity ->
                productEntityMapper.mapFromEntity(productsEntity)
            }.toSet()
        }
        return emptySet()
    }
}