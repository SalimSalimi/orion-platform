package orion.catalog.service.application.categories.usecases.impl

import orion.catalog.service.application.categories.usecases.GetCategoryByIdUsecase
import orion.catalog.service.application.categories.repository.CategoryRepository
import orion.catalog.service.application.utils.Usecase
import orion.catalog.service.domain.category.Category
import java.util.UUID

@Usecase
class GetCategoryByIdUsecaseImpl(
    private val categoryRepository: CategoryRepository
): GetCategoryByIdUsecase {

    override fun execute(input: String): Category? {
        val uuid = UUID.fromString(input)

        return categoryRepository.getById(uuid)
    }
}