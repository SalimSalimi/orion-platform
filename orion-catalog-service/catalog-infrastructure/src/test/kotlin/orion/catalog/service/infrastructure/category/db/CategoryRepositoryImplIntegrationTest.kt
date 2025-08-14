package orion.catalog.service.infrastructure.category.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import orion.catalog.service.application.categories.CategoryRepository
import orion.catalog.service.infrastructure.category.db.dao.CategoryDAO
import orion.catalog.service.infrastructure.category.db.entities.CategoryEntity
import java.time.Instant
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Testcontainers
@DataJpaTest
@Import(CategoryRepositoryImpl::class)
class CategoryRepositoryImplIntegrationTest {

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
    lateinit var categoryDAO: CategoryDAO

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun `should return false because category doesn't existnt`() {
        assertFalse(categoryRepository.existsById(UUID.randomUUID()))
    }

    @Test
    fun `should return true because category exist`() {
        val uuidCategory = UUID.randomUUID()
        categoryDAO.save<CategoryEntity>(CategoryEntity(uuidCategory, "name", "description", Instant.now(), Instant.now()))
        assertTrue(categoryRepository.existsById(uuidCategory))
    }

    @Test
    fun `should return category`() {
        val uuidCategory = UUID.randomUUID()

        categoryDAO.save<CategoryEntity>(CategoryEntity(uuidCategory, "name", "description", Instant.now(), Instant.now()))

        val result = categoryRepository.getById(uuidCategory)

        assertEquals(uuidCategory, result?.id)
        assertEquals("name", result?.name)
    }
}