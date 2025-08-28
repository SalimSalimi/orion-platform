package orion.catalog.catalog_bff.infrastructure.executors

import org.slf4j.LoggerFactory
import org.springframework.cache.CacheManager
import orion.catalog.catalog_bff.application.shared.BaseUsecaseInterface
import orion.catalog.catalog_bff.application.shared.UsecaseExecutorDecorator

class CacheUsecaseResultExecutorImpl<I, R>(
    private val cacheManager: CacheManager,
    private val usecase: BaseUsecaseInterface<I, R>): UsecaseExecutorDecorator<I, R>(usecase) {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun execute(input: I): R {
        val cacheUsecase = cacheManager.getCache("usecaseCache")

        @Suppress("UNCHECKED_CAST")
        val cachedValue: R? = cacheUsecase?.get(input as Any, Any::class.java) as R?
        if (cachedValue != null) {
            logger.info("Get value from cache with parameter $input")
            return cachedValue
        }
        val result = usecase.execute(input)
        cacheUsecase?.put(input as Any, result)
        return result
    }
}