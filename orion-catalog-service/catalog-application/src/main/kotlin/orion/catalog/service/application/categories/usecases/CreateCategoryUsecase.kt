package orion.catalog.service.application.categories.usecases

import orion.catalog.service.application.categories.dto.CreateCategoryDto
import orion.catalog.service.application.shared.UsecaseBaseInterface
import java.util.UUID

interface CreateCategoryUsecase: UsecaseBaseInterface<CreateCategoryDto, UUID> {
}