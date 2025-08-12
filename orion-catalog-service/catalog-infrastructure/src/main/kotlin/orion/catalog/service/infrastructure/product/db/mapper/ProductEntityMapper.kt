package orion.catalog.service.infrastructure.product.db.mapper

import org.springframework.stereotype.Component
import orion.catalog.service.domain.product.Product
import orion.catalog.service.infrastructure.product.db.entities.ProductEntity

@Component
class ProductEntityMapper {

    fun mapFromEntity(productEntity: ProductEntity): Product {
        with(productEntity) {
            return Product(
                id,
                name,
                description,
                price,
                availableStock = stock,
                categoryId = emptySet()
            )
        }
    }

    fun mapToEntity(product: Product): ProductEntity {
        return ProductEntity(
            id = product.uuid,
            name = product.name,
            description = product.description,
            price = product.price,
            stock = product.availableStock,
        )
    }
}