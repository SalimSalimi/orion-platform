package orion.catalog.catalog_bff.application.shared.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Usecase(
    val cache: Boolean = false,
) {
}

