package orion.catalog.service.presentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import orion.catalog.service.infrastructure.config.DatabaseConfig
import orion.catalog.service.infrastructure.config.UsecasesConfig

@SpringBootApplication
@Import(UsecasesConfig::class, DatabaseConfig::class)
class OrionCatalogServiceApplication

fun main(args: Array<String>) {
    runApplication<OrionCatalogServiceApplication>(*args)
}