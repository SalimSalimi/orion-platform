package orion.catalog.catalog_bff.application.categories

import orion.catalog.catalog_bff.application.shared.BaseUsecaseInterface
import orion.catalog.catalog_bff.domain.category.Category

interface GetCategoryByIdUsecase: BaseUsecaseInterface<String, Category?>