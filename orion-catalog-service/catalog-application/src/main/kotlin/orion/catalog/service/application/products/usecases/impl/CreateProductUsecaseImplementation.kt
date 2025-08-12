package orion.catalog.service.application.products.usecases.impl

import orion.catalog.service.application.products.dto.CreateProductDto
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.application.products.usecases.CreateProductUsecase
import orion.catalog.service.application.utils.Usecase
import orion.catalog.service.domain.product.Product
import java.util.UUID

@Usecase
class CreateProductUsecaseImplementation(
    private val productRepository: ProductRepository,
) : CreateProductUsecase {

    override fun execute(input: CreateProductDto): UUID {
        val product = Product(
            uuid = UUID.randomUUID(),
            name = input.name,
            description = input.description,
            price = input.price,
            availableStock = input.stock,
            categoryId = input.categoryIds.toMutableSet(),
        )
        // Save product
        return productRepository.create(product)
    }

}