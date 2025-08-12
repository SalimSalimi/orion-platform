package orion.catalog.service.presentation.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import orion.catalog.service.application.products.dto.CreateProductDto
import orion.catalog.service.application.products.dto.ProductDto
import orion.catalog.service.application.products.usecases.CreateProductUsecase
import orion.catalog.service.application.products.usecases.GetAllProductsUsecase
import orion.catalog.service.application.products.usecases.GetProductByIdUsecase

@RestController
@RequestMapping("/products")
class ProductController(
    private val createProductUsecase: CreateProductUsecase,
    private val getProductByIdUsecase: GetProductByIdUsecase,
    private val getAllProductsUsecase: GetAllProductsUsecase,
) {

    @PostMapping
    fun createProduct(@RequestBody productDto: CreateProductDto): ResponseEntity<CreatedProductDtoSucces> {
        val createdProductId = this.createProductUsecase.execute(productDto)
        val response = CreatedProductDtoSucces(createdProductId.toString())
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: String): ResponseEntity<ProductDto> {
        val productDto = getProductByIdUsecase.execute(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(productDto)
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<Set<ProductDto>> {
        val allProducts = getAllProductsUsecase.execute(Unit)
        return ResponseEntity.ok(allProducts)
    }
}


data class CreatedProductDtoSucces(
    @JsonProperty
    val id: String
)