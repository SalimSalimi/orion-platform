package orion.catalog.catalog_bff.application.shared

interface ParallelUsecaseExecutor {

    fun <I, R> executeAll(usecases: List<Pair<BaseUsecaseInterface<I, R>, I>>): List<R>
}