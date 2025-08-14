package orion.catalog.service.presentation.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import orion.catalog.service.application.categories.dto.CreateCategoryDto
import orion.catalog.service.application.categories.usecases.CreateCategoryUsecase
import orion.catalog.service.application.categories.usecases.GetCategoryByIdUsecase
import orion.catalog.service.domain.category.Category

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val getCategoryByIdUsecase: GetCategoryByIdUsecase,
    private val createCategoryUsecase: CreateCategoryUsecase
) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Category> {
        val category = this.getCategoryByIdUsecase.execute(id)

        return if (category == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(category)
        }
    }

    @PostMapping
    fun createProduct(dto: CreateCategoryDto): ResponseEntity<CreatedProductDto> {
        val categoryId = createCategoryUsecase.execute(dto)

        return ResponseEntity.ok(CreatedProductDto(categoryId.toString()))
    }
}

data class CreatedProductDto(
    val id: String
)