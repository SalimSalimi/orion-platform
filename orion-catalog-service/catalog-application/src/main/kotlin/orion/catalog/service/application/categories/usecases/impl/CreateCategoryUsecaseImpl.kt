package orion.catalog.service.application.categories.usecases.impl

import orion.catalog.service.application.categories.dto.CreateCategoryDto
import orion.catalog.service.application.categories.repository.CategoryRepository
import orion.catalog.service.application.categories.usecases.CreateCategoryUsecase
import orion.catalog.service.application.utils.Usecase
import orion.catalog.service.domain.category.Category
import java.util.UUID

@Usecase
class CreateCategoryUsecaseImpl(
    private val categoryRepository: CategoryRepository
): CreateCategoryUsecase {

    override fun execute(input: CreateCategoryDto): UUID {
        val categoryToCreate = Category(
            UUID.randomUUID(),
            input.name,
            input.description
        )

        val createdCategory = categoryRepository.create(categoryToCreate)

        return createdCategory.id
    }
}