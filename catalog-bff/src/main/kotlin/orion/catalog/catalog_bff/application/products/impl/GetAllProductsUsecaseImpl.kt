package orion.catalog.catalog_bff.application.products.impl

import orion.catalog.catalog_bff.application.products.GetAllProductsUsecase
import orion.catalog.catalog_bff.application.products.repository.ProductRepository
import orion.catalog.catalog_bff.application.shared.annotation.Usecase
import orion.catalog.catalog_bff.domain.product.Product

@Usecase(cache = true)
class GetAllProductsUsecaseImpl(
    private val productRepository: ProductRepository
): GetAllProductsUsecase {

    override fun execute(input: Unit): List<Product> {
        return this.productRepository.getAll()
    }
}