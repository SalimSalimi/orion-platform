package orion.catalog.catalog_bff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class CatalogBffApplication

fun main(args: Array<String>) {
	runApplication<CatalogBffApplication>(*args)
}
