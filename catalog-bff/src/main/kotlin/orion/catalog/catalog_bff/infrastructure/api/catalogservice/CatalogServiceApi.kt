package orion.catalog.catalog_bff.infrastructure.api.catalogservice

import orion.catalog.catalog_bff.infrastructure.api.catalogservice.dto.ProductDtoCatalogService

interface CatalogServiceApi {

    fun getAllProducts(): List<ProductDtoCatalogService>
}