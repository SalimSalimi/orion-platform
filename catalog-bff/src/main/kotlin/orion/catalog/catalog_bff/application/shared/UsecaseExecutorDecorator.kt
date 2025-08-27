package orion.catalog.catalog_bff.application.shared

open class UsecaseExecutorDecorator<I, R>(
    private val usecase: BaseUsecaseInterface<I, R>
): BaseUsecaseInterface<I, R> {

    override fun execute(input: I): R {
        return this.usecase.execute(input)
    }

}