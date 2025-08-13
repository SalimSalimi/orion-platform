package orion.catalog.catalog_bff.application.products.impl

import org.mockito.Mockito
import org.mockito.Mockito.mock
import orion.catalog.catalog_bff.application.products.repository.ProductRepository
import orion.catalog.catalog_bff.domain.product.Product
import kotlin.test.Test
import kotlin.test.assertEquals

class GetAllProductsUsecaseImplTest {
    private val productRepository: ProductRepository = mock()

    private val usecase: GetAllProductsUsecaseImpl = GetAllProductsUsecaseImpl(productRepository)

    @Test
    fun `should retrieve all products and return result`() {
        val products = emptyList<Product>()
        Mockito.`when`(productRepository.getAll()).thenReturn(products)

        val result = usecase.execute()

        assertEquals(products, result)
    }
}