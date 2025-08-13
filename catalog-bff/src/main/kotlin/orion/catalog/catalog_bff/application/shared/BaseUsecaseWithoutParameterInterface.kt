package orion.catalog.catalog_bff.application.shared

interface BaseUsecaseWithoutParameterInterface<R>: BaseUsecaseInterface<Unit, R> {

    fun execute(): R = execute(Unit)
}