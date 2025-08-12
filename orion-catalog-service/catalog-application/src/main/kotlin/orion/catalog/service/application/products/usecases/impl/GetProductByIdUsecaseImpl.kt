package orion.catalog.service.application.products.usecases.impl

import orion.catalog.service.application.products.dto.ProductDto
import orion.catalog.service.application.products.repository.ProductRepository
import orion.catalog.service.application.products.usecases.GetProductByIdUsecase
import orion.catalog.service.application.utils.Usecase
import java.util.UUID

@Usecase
class GetProductByIdUsecaseImpl(
    private val productRepository: ProductRepository
): GetProductByIdUsecase{

    override fun execute(input: String): ProductDto? {
        if (input.isBlank()) {
            throw RuntimeException("ID MUST NOT BE NULL")
        } else {
            val uuidId = UUID.fromString(input)
            val product = productRepository.getById(uuidId)

            if (product == null) {
                return null
            } else {
                with(product) {
                    return ProductDto(
                        uuidId.toString(),
                        name,
                        description,
                        price,
                        availableStock
                    )
                }
            }
        }
    }
}