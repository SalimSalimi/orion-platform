package orion.catalog.service.application.products.usecases

import orion.catalog.service.application.products.dto.CreateProductDto
import orion.catalog.service.application.shared.UsecaseBaseInterface
import java.util.UUID

interface CreateProductUsecase: UsecaseBaseInterface<CreateProductDto, UUID>