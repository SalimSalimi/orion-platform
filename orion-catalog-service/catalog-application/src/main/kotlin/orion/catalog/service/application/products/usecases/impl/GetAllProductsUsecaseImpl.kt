package orion.catalog.service.application.products.usecases.impl

import orion.catalog.service.application.products.dto.ProductDto
import orion.catalog.service.application.products.mapper.ProductMapper
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.application.products.usecases.GetAllProductsUsecase

class GetAllProductsUsecaseImpl(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
): GetAllProductsUsecase {

    override fun execute(input: Unit): Set<ProductDto> {
        val products = this.productRepository.getAll()
        if (products.isEmpty()) {
            return emptySet()
        } else {
            return products.map { product ->
                productMapper.toDto(product)
            }.toSet()
        }
    }
}