package orion.catalog.catalog_bff.infrastructure.api.catalogservice.categories

import orion.catalog.catalog_bff.infrastructure.api.catalogservice.categories.dto.CategoryCatalogServiceDTO

interface CategoriesServiceApi {

    fun getById(id: String): CategoryCatalogServiceDTO?
}