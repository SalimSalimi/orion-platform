package orion.catalog.service.application.products.usecases.impl

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import orion.catalog.service.application.categories.repository.CategoryRepository
import orion.catalog.service.application.products.dto.CreateProductDto
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.domain.product.Product
import java.util.UUID
import kotlin.test.assertEquals

class CreateProductUsecaseImplementationTest {
    private val productRepository: ProductRepository = mock()
    private val categoryRepository: CategoryRepository = mock()

    private val createProductUsecase: CreateProductUsecaseImplementation =
        CreateProductUsecaseImplementation(productRepository)

    @Test
    fun ` createProduct should create a product succesfully `() {
        val productCaptor = argumentCaptor<Product>()
        val categoryId = UUID.randomUUID()
        val productIdCreated = UUID.randomUUID()

        val productDto = CreateProductDto(
            "productName",
            "A product",
            15.00,
            2,
            listOf(categoryId)
        )

        //whenever(categoryRepository.existsById(categoryId)).thenReturn(true)
        whenever(productRepository.create(any())).thenReturn(productIdCreated)

        val productId = createProductUsecase.execute(productDto)

        assertEquals(productIdCreated, productId)
        verify(productRepository).create(productCaptor.capture())
        val savedProduct = productCaptor.firstValue

        assertEquals(productDto.name, savedProduct.name)
        assertEquals(productDto.description, savedProduct.description)
        assertEquals(productDto.price, savedProduct.price)
        assertEquals(productDto.stock, savedProduct.availableStock)
    }
}