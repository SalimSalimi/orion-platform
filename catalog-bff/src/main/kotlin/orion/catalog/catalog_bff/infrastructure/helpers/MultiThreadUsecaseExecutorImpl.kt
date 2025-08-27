package orion.catalog.catalog_bff.infrastructure.helpers

import org.slf4j.LoggerFactory
import orion.catalog.catalog_bff.application.shared.BaseUsecaseInterface
import orion.catalog.catalog_bff.application.shared.UsecaseExecutorDecorator
import java.util.concurrent.Executors

class MultiThreadUsecaseExecutorImpl<I, R>
    (usecase: BaseUsecaseInterface<I, R>) : UsecaseExecutorDecorator<I, R>(usecase) {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun execute(input: I): R {
        val virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()
        val result: R by lazy {
            var threadResult: R? = null
            virtualExecutor.execute {
                logger.info("Executing in a virtual thread")
                threadResult = super.execute(input)
            }
            threadResult!!
        }

        return result
    }
}
