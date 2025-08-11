package orion.catalog.service.application.products.repository

import orion.catalog.service.domain.product.Product
import java.util.UUID

interface ProductRepository {

    fun create(product: Product): UUID

    fun getById(id: UUID): Product?
}