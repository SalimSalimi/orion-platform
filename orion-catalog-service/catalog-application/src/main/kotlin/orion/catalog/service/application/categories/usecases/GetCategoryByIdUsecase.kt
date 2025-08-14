package orion.catalog.service.application.categories.usecases

import orion.catalog.service.application.shared.UsecaseBaseInterface
import orion.catalog.service.domain.category.Category

interface GetCategoryByIdUsecase: UsecaseBaseInterface<String, Category?>