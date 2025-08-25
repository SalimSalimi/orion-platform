package orion.catalog.catalog_bff.application.categories.repository

import orion.catalog.catalog_bff.domain.category.Category

interface CategoryRepository {

    fun getById(id: String): Category?
}