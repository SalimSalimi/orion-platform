package orion.catalog.service.application.shared

interface BaseUsecaseWithoutParameterInterface<R>: UsecaseBaseInterface<Unit, R> {

    fun execute(): R = execute(Unit)
}