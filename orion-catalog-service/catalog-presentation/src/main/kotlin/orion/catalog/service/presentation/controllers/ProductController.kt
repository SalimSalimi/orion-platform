package orion.catalog.service.presentation.controllers

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
import orion.catalog.service.application.products.usecases.GetProductByIdUsecase

@RestController
@RequestMapping("/products")
class ProductController(
    private val createProductUsecase: CreateProductUsecase,
    private val getProductByIdUsecase: GetProductByIdUsecase,
) {

    @PostMapping
    fun createProduct(@RequestBody productDto: CreateProductDto): ResponseEntity<Unit> {
        this.createProductUsecase.execute(productDto)
        return ResponseEntity.status(201).build()
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: String): ResponseEntity<ProductDto> {
        val productDto = getProductByIdUsecase.execute(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(productDto)
    }
}