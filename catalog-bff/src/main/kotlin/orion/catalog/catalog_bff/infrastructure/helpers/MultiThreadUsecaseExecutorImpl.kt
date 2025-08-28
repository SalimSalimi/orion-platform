package orion.catalog.catalog_bff.infrastructure.helpers

import org.slf4j.LoggerFactory
import orion.catalog.catalog_bff.application.shared.BaseUsecaseInterface
import orion.catalog.catalog_bff.application.shared.UsecaseExecutorDecorator
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MultiThreadUsecaseExecutorImpl<I, R>
    (usecase: BaseUsecaseInterface<I, R>) : UsecaseExecutorDecorator<I, R>(usecase) {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val virtualExecutor: ExecutorService = Executors.newVirtualThreadPerTaskExecutor()

    override fun execute(input: I): R {
        val result: Future<*> = virtualExecutor.submit<R> {
            logger.info("Executing in a virtual thread")
            super.execute(input)
        }
        @Suppress("UNCHECKED_CAST")
        return result.get() as R
    }
}
