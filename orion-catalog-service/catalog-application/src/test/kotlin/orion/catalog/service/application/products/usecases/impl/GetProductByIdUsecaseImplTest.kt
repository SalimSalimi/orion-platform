package orion.catalog.service.application.products.usecases.impl

import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import orion.catalog.service.application.products.dto.ProductDto
import orion.catalog.service.application.products.mapper.ProductMapper
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.application.products.usecases.GetProductByIdUsecase
import orion.catalog.service.domain.product.Product
import java.util.UUID
import kotlin.test.Test

class GetProductByIdUsecaseImplTest {
    private val productRepository: ProductRepository = mock()
    private val productMapper: ProductMapper = mock()

    private val usecase: GetProductByIdUsecase = GetProductByIdUsecaseImpl(productRepository, productMapper)


    @Test
    fun `should retrieve product per id and return result `() {
        val randomUUID = UUID.randomUUID()
        val product = Product(randomUUID, "a product", "descriptiopn", 15.00, 2, emptySet())
        val expectedProduct = ProductDto(randomUUID.toString(), "a product", "descriptiopn", 15.00, 2)

        whenever(productRepository.getById(randomUUID)).thenReturn(product)
        whenever(productMapper.toDto(product)).thenReturn(expectedProduct)

        val result = usecase.execute(randomUUID.toString())

        assertNotNull(result)
        assertEquals(expectedProduct, result)
    }
}