package orion.catalog.catalog_bff.infrastructure.api.catalogservice

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientApiConfig {

    @Bean
    @Qualifier("catalogApiRestClient")
    fun restClient(builder: RestClient.Builder,
                   @Value("\${api.catalog.base-url}") baseUrl: String) =
        builder.baseUrl(baseUrl).build()
}