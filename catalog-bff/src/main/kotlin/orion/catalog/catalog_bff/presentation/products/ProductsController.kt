package orion.catalog.catalog_bff.presentation.products

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import orion.catalog.catalog_bff.application.products.GetAllProductsWithCategoriesUsecase
import orion.catalog.catalog_bff.application.products.dto.ProductWithCategoryDto

@RestController
@RequestMapping("/products")
class ProductsController(
    //private val getAllProductsUsecase: GetAllProductsUsecase
    private val getProductsWithCategories: GetAllProductsWithCategoriesUsecase
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductWithCategoryDto>> {
        return ResponseEntity.ok(getProductsWithCategories.execute())
    }
}