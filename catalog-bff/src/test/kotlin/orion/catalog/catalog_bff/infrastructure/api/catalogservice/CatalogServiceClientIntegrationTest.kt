package orion.catalog.catalog_bff.infrastructure.api.catalogservice

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import kotlin.test.Test

@RestClientTest(CatalogServiceClient::class, RestClientApiConfig::class)
class CatalogServiceClientIntegrationTest {

    @Autowired
    lateinit var server: MockRestServiceServer

    @Autowired
    lateinit var catalogServiceClient: CatalogServiceClient

    @Value("\${api.catalog.base-url}")
    lateinit var baseUrl: String

    @Test
    fun `should calls products and return values `() {
        val jsonResponse = """
            [
              {
                "id": "bfb0ecc5-1e9d-4bc5-a33e-7e0641b691aa",
                "name": "First product",
                "description": "this is my first product created through Bruno with OAuth2",
                "price": 150,
                "availableInStock": 5
              },
              {
                "id": "0fbcce9d-c06c-4228-876b-49e48955e56e",
                "name": "Second product",
                "description": "this is my second product created through Bruno with OAuth2",
                "price": 50,
                "availableInStock": 155
              }
            ]
        """.trimIndent()

        server.expect(requestTo("$baseUrl/products"))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON))


        val products = catalogServiceClient.getAllProducts()

        assertNotNull(products)
        assertEquals(2, products.size)
    }
}