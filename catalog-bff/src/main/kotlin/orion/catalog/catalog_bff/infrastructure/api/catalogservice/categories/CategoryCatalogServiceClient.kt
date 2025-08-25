package orion.catalog.catalog_bff.infrastructure.api.catalogservice.categories

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import orion.catalog.catalog_bff.infrastructure.api.catalogservice.categories.dto.CategoryCatalogServiceDTO

@Component
class CategoryCatalogServiceClient(
    @Qualifier("catalogApiRestClient") private val restClient: RestClient
): CategoriesServiceApi {

    override fun getById(id: String): CategoryCatalogServiceDTO? {
        return restClient.get()
            .uri("/categories/${id}")
            .retrieve()
            .body(CategoryCatalogServiceDTO::class.java)
    }
}