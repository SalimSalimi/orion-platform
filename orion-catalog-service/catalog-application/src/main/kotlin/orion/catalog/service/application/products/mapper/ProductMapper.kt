package orion.catalog.service.application.products.mapper

import orion.catalog.service.application.products.dto.ProductDto
import orion.catalog.service.application.utils.ApplicationMapper
import orion.catalog.service.domain.product.Product

@ApplicationMapper
class ProductMapper {

    fun toDto(product: Product): ProductDto {
        with(product) {
            return ProductDto(
                product.uuid.toString(),
                name,
                description,
                price,
                availableStock
            )
        }
    }

}