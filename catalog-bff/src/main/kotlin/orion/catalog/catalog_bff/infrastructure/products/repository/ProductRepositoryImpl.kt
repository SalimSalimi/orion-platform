package orion.catalog.catalog_bff.infrastructure.products.repository

import org.springframework.stereotype.Repository
import orion.catalog.catalog_bff.application.products.repository.ProductRepository
import orion.catalog.catalog_bff.domain.product.Product
import orion.catalog.catalog_bff.infrastructure.api.catalogservice.CatalogServiceClient
import orion.catalog.catalog_bff.infrastructure.api.catalogservice.dto.ProductDtoCatalogService

@Repository
class ProductRepositoryImpl(
    private val catalogServiceClient: CatalogServiceClient
): ProductRepository {

    override fun getAll(): List<Product> {
        val productsDto: List<ProductDtoCatalogService> = this.catalogServiceClient.getAllProducts()

        return if (productsDto.isEmpty()) {
            emptyList()
        } else {
            productsDto.map {
                Product(
                    it.name,
                    it.price,
                    it.availableInStock,
                    it.categories
                )
            }
        }
    }
}