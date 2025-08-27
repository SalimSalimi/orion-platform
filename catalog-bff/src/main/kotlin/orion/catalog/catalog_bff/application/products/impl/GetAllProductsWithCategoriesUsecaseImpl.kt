package orion.catalog.catalog_bff.application.products.impl

import orion.catalog.catalog_bff.application.categories.GetCategoryByIdUsecase
import orion.catalog.catalog_bff.application.products.GetAllProductsWithCategoriesUsecase
import orion.catalog.catalog_bff.application.products.dto.CategoryDto
import orion.catalog.catalog_bff.application.products.dto.ProductWithCategoryDto
import orion.catalog.catalog_bff.application.products.repository.ProductRepository
import orion.catalog.catalog_bff.application.shared.annotation.Usecase
import orion.catalog.catalog_bff.domain.category.Category
import orion.catalog.catalog_bff.domain.product.Product
import orion.catalog.catalog_bff.infrastructure.helpers.MultiThreadUsecaseExecutorImpl

@Usecase
class GetAllProductsWithCategoriesUsecaseImpl(
    private val productRepository: ProductRepository,
    private val getCategoryByIdUsecase: GetCategoryByIdUsecase
): GetAllProductsWithCategoriesUsecase {

    override fun execute(input: Unit): List<ProductWithCategoryDto> {
        val products = productRepository.getAll()
        val result: MutableList<ProductWithCategoryDto> = ArrayList()

        if (products.isNotEmpty()) {
            products.forEach { product ->
                run {
                    val categories = getCategories(product.categoriesId)
                    result.add(mapProductWithCategories(product, categories))
                }
            }
        }
        return result
    }


    private fun getCategories(categoriesId: Set<String>): Set<Category> {
        val result: MutableSet<Category> = HashSet()
        categoriesId.forEach { categoryId ->
            run {
                val multiThreadExecutor: MultiThreadUsecaseExecutorImpl<String, Category?> = MultiThreadUsecaseExecutorImpl(getCategoryByIdUsecase)
                val category = multiThreadExecutor.execute(categoryId)
                if (category != null) {
                    result.add(category)
                }
            }
        }
        return result
    }

    private fun mapProductWithCategories(product: Product, categories: Set<Category>): ProductWithCategoryDto {
        return ProductWithCategoryDto(
            product.name,
            categories.map { category ->
                CategoryDto(category.name, category.description)
            }.toSet()
        )
    }
}