package orion.catalog.catalog_bff.infrastructure.api.catalogservice

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import orion.catalog.catalog_bff.infrastructure.api.catalogservice.dto.ProductDtoCatalogService

@Component
class CatalogServiceClient(
    @Qualifier("catalogApiRestClient") private val restClient: RestClient
): CatalogServiceApi {

    override fun getAllProducts(): List<ProductDtoCatalogService> {
        val result = restClient.get()
            .uri("/products")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<ProductDtoCatalogService>>() {})

        return result ?: emptyList()
    }
}