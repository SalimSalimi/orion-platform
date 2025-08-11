package orion.catalog.service.infrastructure.product.db

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.domain.product.Product
import orion.catalog.service.infrastructure.product.db.jpa.ProductJpaRepository
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

@Testcontainers
@DataJpaTest
@Import(ProductRepositoryImpl::class)
class ProductRepositoryImplTest {
    companion object {
        // Init PostgresDB container + properties
        @Container
        val postgres = PostgreSQLContainer("postgres:16.3").apply {
            withDatabaseName("testdb")
            withUsername("test")
            withPassword("test")
        }

        @JvmStatic
        @DynamicPropertySource
        fun registerPgProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.jpa.hibernate.ddl-auto") { "create-drop" }
        }
    }

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var productJpaRepository: ProductJpaRepository

    @BeforeEach
    fun setup() {
        productJpaRepository.deleteAll()
    }

    @Test
    fun `should save and retrieve a product `() {
        val product = Product(
            uuid = UUID.randomUUID(),
            name = "a Product",
            description = "Description",
            categoryId = emptySet(),
            price = 150.00,
            availableStock = 5
        )

        val result = productRepository.create(product)

        assertEquals(product.uuid, result)

        val createdProduct = productJpaRepository.findById(result)

        createdProduct.ifPresent { productEntity ->
            with(productEntity) {
                assertEquals(product.price, price)
                assertEquals(product.name, name)
                assertEquals(product.availableStock, stock)
                assertEquals(product.description, description)
            }

        }
    }
}