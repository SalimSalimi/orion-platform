package orion.catalog.service.infrastructure.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import orion.catalog.service.application.utils.Usecase

@Configuration
@ComponentScan(
    basePackages = ["orion.catalog.service"],
    includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, value = [Usecase::class])]
)
class UsecasesConfig