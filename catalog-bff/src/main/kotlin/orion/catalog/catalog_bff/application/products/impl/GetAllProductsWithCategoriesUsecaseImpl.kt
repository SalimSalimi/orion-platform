package orion.catalog.catalog_bff.application.products.impl

import org.slf4j.LoggerFactory
import orion.catalog.catalog_bff.application.categories.GetCategoryByIdUsecase
import orion.catalog.catalog_bff.application.products.GetAllProductsWithCategoriesUsecase
import orion.catalog.catalog_bff.application.products.dto.CategoryDto
import orion.catalog.catalog_bff.application.products.dto.ProductWithCategoryDto
import orion.catalog.catalog_bff.application.products.repository.ProductRepository
import orion.catalog.catalog_bff.application.shared.ParallelUsecaseExecutor
import orion.catalog.catalog_bff.application.shared.annotation.Usecase
import orion.catalog.catalog_bff.domain.category.Category
import orion.catalog.catalog_bff.domain.product.Product
import orion.catalog.catalog_bff.infrastructure.executors.MultiThreadUsecaseExecutorImpl
import kotlin.system.measureTimeMillis

@Usecase
class GetAllProductsWithCategoriesUsecaseImpl(
    private val productRepository: ProductRepository,
    private val getCategoryByIdUsecase: GetCategoryByIdUsecase,
    private val parallelUsecaseExecutor: ParallelUsecaseExecutor,
): GetAllProductsWithCategoriesUsecase {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun execute(input: Unit): List<ProductWithCategoryDto> {
        val products = productRepository.getAll()
        val result: MutableList<ProductWithCategoryDto> = ArrayList()

        if (products.isNotEmpty()) {
            products.forEach { product ->
                run {
                    var categories: Set<Category> = HashSet()
                    val duration = measureTimeMillis {
                        categories = getCategoriesInParallel(product.categoriesId)
                    }
                    logger.info("Execution time of usecase: $duration ms")
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

    private fun getCategoriesInParallel(categoriesId: Set<String>): Set<Category> {
        val pairCategories: List<Pair<GetCategoryByIdUsecase, String>> = categoriesId.map { categoryId ->
            Pair(getCategoryByIdUsecase, categoryId)
        }
        @Suppress("UNCHECKED_CAST")
        return parallelUsecaseExecutor.executeAll(pairCategories).toSet() as Set<Category>
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