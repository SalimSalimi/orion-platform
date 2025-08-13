package orion.catalog.catalog_bff.application.shared

interface BaseUsecaseInterface<I, R> {

    fun execute(input: I): R
}