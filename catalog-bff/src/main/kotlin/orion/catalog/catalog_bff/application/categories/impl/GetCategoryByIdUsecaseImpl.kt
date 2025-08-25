package orion.catalog.catalog_bff.application.categories.impl

import orion.catalog.catalog_bff.application.categories.GetCategoryByIdUsecase
import orion.catalog.catalog_bff.application.categories.repository.CategoryRepository
import orion.catalog.catalog_bff.application.shared.annotation.Usecase
import orion.catalog.catalog_bff.domain.category.Category

@Usecase
class GetCategoryByIdUsecaseImpl(
    private val categoryRepository: CategoryRepository
): GetCategoryByIdUsecase {

    override fun execute(input: String): Category? {
        return categoryRepository.getById(input)
    }
}