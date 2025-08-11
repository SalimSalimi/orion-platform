package orion.catalog.service.application.products.usecases

import orion.catalog.service.application.products.dto.CreateProductDto
import orion.catalog.service.application.shared.UsecaseBaseInterface

interface CreateProductUsecase: UsecaseBaseInterface<CreateProductDto, Unit>