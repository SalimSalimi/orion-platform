package orion.catalog.service.presentation.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import orion.catalog.service.application.products.dto.CreateProductDto
import orion.catalog.service.application.products.dto.ProductDto
import orion.catalog.service.application.products.usecases.CreateProductUsecase
import orion.catalog.service.application.products.usecases.GetAllProductsUsecase
import orion.catalog.service.application.products.usecases.GetProductByIdUsecase
import java.util.UUID

@WebMvcTest(ProductController::class)
class ProductControllerIntegrationTest(@Autowired val mockMvc: MockMvc) {

    @MockitoBean
    lateinit var getProductByIdUsecase: GetProductByIdUsecase

    @MockitoBean
    lateinit var createProductUsecase: CreateProductUsecase

    @MockitoBean
    lateinit var getAllProductUsecase: GetAllProductsUsecase

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @WithMockUser
    fun `GET {id} should return product per id `() {
        val id = UUID.randomUUID()
        val stringId = id.toString()
        val product = ProductDto(stringId, "Mock Product", "desc", 10.00, 5)

        whenever(getProductByIdUsecase.execute(stringId)).thenReturn(product)

        mockMvc.perform(get("/products/$stringId"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(stringId))
            .andExpect(jsonPath("$.name").value("Mock Product"))

        verify(getProductByIdUsecase, times(1)).execute(any())
    }

    @Test
    @WithMockUser
    fun `GET all should return all products `() {
        val id = UUID.randomUUID()
        val stringId = id.toString()
        val product = ProductDto(stringId, "Mock Product", "desc", 10.00, 5)

        whenever(getAllProductUsecase.execute(Unit)).thenReturn(setOf(product))

        mockMvc.perform(get("/products"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$", hasSize<Int>(1)))
            .andExpect(jsonPath("$[0].id").value(stringId))
            .andExpect(jsonPath("$[0].name").value("Mock Product"))

        verify(getAllProductUsecase, times(1)).execute(Unit)
    }

    @Test
    @WithMockUser
    fun `POST {id} should create product and return 200 with id created `() {
        val productToCreateDto = CreateProductDto("A product", "description", 10.00, 5, emptyList())

        val productToCreateJson = objectMapper.writeValueAsBytes(productToCreateDto)
        val uuidCreated = UUID.randomUUID()

        whenever(createProductUsecase.execute(productToCreateDto)).thenReturn(uuidCreated)

        mockMvc.perform(
            post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productToCreateJson)
                .with(csrf())

        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(uuidCreated.toString()))

        verify(createProductUsecase, times(1)).execute(any())
    }

}