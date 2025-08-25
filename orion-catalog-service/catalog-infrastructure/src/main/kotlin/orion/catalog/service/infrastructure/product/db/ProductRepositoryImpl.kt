package orion.catalog.service.infrastructure.product.db

import org.springframework.stereotype.Repository
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.domain.product.Product
import orion.catalog.service.infrastructure.category.db.dao.CategoryDAO
import orion.catalog.service.infrastructure.category.db.entities.CategoryEntity
import orion.catalog.service.infrastructure.product.db.entities.ProductEntity
import orion.catalog.service.infrastructure.product.db.jpa.ProductJpaRepository
import orion.catalog.service.infrastructure.product.db.mapper.ProductEntityMapper
import java.util.*

@Repository
class ProductRepositoryImpl(
    private val jpaRepository: ProductJpaRepository,
    private val productEntityMapper: ProductEntityMapper,
    private val categoryRepository: CategoryDAO,
): ProductRepository {

    override fun create(product: Product): UUID {
        val productEntity = productEntityMapper.mapToEntity(product)
        if (product.categoryId?.isNotEmpty() == true) {
            val categories: MutableSet<CategoryEntity> = product.categoryId!!.map {
                categoryRepository.getReferenceById(it)
            }.toMutableSet()
            productEntity.categories = categories
        }

        val savedEntity = this.jpaRepository.save<ProductEntity>(productEntity)
        return savedEntity.id
    }

    override fun getById(id: UUID): Product? {
        val productEntity = jpaRepository.findById(id)
        return if (productEntity.isPresent) {
            productEntityMapper.mapFromEntity(productEntity.get())
        } else {
            null
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