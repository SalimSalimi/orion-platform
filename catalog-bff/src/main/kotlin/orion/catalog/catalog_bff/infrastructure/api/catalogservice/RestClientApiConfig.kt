package orion.catalog.catalog_bff.infrastructure.api.catalogservice

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor
import org.springframework.web.client.RestClient


@Configuration
class RestClientApiConfig(
    private val authorizedClientManager: OAuth2AuthorizedClientManager
) {

    @Bean
    @Qualifier("catalogApiRestClient")
    fun restClient(builder: RestClient.Builder, @Value("\${api.catalog.base-url}") baseUrl: String): RestClient {
        val requestInterceptor =
            OAuth2ClientHttpRequestInterceptor(authorizedClientManager)

        requestInterceptor.setClientRegistrationIdResolver { request -> "catalog-bff-credentials" }

        return builder
            .baseUrl(baseUrl)
            .requestInterceptor(requestInterceptor)
            .build()
    }
}