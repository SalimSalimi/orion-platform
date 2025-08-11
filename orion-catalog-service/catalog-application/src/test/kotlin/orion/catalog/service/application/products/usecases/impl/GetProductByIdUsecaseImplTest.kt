package orion.catalog.service.application.products.usecases.impl

import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.application.products.usecases.GetProductByIdUsecase
import orion.catalog.service.domain.product.Product
import java.util.UUID
import kotlin.test.Test

class GetProductByIdUsecaseImplTest {
    private val productRepository: ProductRepository = mock()

    private val usecase: GetProductByIdUsecase = GetProductByIdUsecaseImpl(productRepository)


    @Test
    fun `should retrieve product per id and return result `() {
        val randomUUID = UUID.randomUUID()
        val product = Product(randomUUID, "a product", "descriptiopn", 15.00, 2, emptySet())

        whenever(productRepository.getById(randomUUID)).thenReturn(product)

        val result = usecase.execute(randomUUID.toString())

        assertNotNull(result)
        assertEquals(randomUUID.toString(), result!!.id)
    }
}