package orion.catalog.service.application.shared

interface UsecaseBaseInterface<I, R> {

    fun execute(input: I): R
}