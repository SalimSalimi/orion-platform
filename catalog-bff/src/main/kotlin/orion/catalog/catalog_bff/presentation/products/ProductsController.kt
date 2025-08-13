package orion.catalog.catalog_bff.presentation.products

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import orion.catalog.catalog_bff.application.products.GetAllProductsUsecase
import orion.catalog.catalog_bff.domain.product.Product

@RestController
@RequestMapping("/products")
class ProductsController(
    private val getAllProductsUsecase: GetAllProductsUsecase
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(getAllProductsUsecase.execute())
    }
}