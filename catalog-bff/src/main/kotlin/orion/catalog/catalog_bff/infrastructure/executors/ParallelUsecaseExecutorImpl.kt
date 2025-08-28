package orion.catalog.catalog_bff.infrastructure.executors

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import orion.catalog.catalog_bff.application.shared.BaseUsecaseInterface
import orion.catalog.catalog_bff.application.shared.ParallelUsecaseExecutor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

@Component
class ParallelUsecaseExecutorImpl: ParallelUsecaseExecutor {
    companion object {
        private val logger = LoggerFactory.getLogger(ParallelUsecaseExecutorImpl::class.java)
    }

    private val executorService: ExecutorService = Executors.newVirtualThreadPerTaskExecutor()

    override fun <I, R> executeAll(usecases: List<Pair<BaseUsecaseInterface<I, R>, I>>): List<R> {
        val futures: List<Future<R>> = usecases.map { (usecase, input) ->
            executorService.submit<R> {
                logger.info("Executing in parallel $input")
                usecase.execute(input)
            }
        }

        return futures.map { it.get() }
    }
}