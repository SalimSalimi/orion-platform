package orion.catalog.service.application.products.usecases.impl

import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import orion.catalog.service.application.products.mapper.ProductMapper
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.domain.product.Product
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class GetAllProductsUsecaseImplTest {
    private val productRepository: ProductRepository = mock()
    private val productMapper: ProductMapper = mock()

    private val usecase: GetAllProductsUsecaseImpl = GetAllProductsUsecaseImpl(productRepository, productMapper)

    @Test
    fun `should retrieve all products`() {
        val product = Product(
            UUID.randomUUID(),
            "A product",
            "description",
            150.00,
            5,
            emptySet()
        )

        val productsFromRepository = setOf(product)

        whenever(productRepository.getAll()).thenReturn(productsFromRepository)

        val result = usecase.execute(Unit)

        assertNotNull(result)
        assertEquals(1, result.size)
    }
}