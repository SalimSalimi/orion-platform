package orion.catalog.catalog_bff.application.products.repository

import orion.catalog.catalog_bff.domain.product.Product

interface ProductRepository {

    fun getAll(): List<Product>
}