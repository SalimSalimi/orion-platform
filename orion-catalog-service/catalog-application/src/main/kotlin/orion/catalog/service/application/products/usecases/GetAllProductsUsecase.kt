package orion.catalog.service.application.products.usecases

import orion.catalog.service.application.products.dto.ProductDto
import orion.catalog.service.application.shared.UsecaseBaseInterface

interface GetAllProductsUsecase: UsecaseBaseInterface<Unit, Set<ProductDto>>