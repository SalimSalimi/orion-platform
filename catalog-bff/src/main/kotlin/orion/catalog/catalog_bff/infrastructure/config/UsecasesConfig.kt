package orion.catalog.catalog_bff.infrastructure.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import orion.catalog.catalog_bff.application.shared.annotation.Usecase

@Configuration
@ComponentScan(
    basePackages = ["orion.catalog.catalog_bff"],
    includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, value = [Usecase::class])]
)
class UsecasesConfig