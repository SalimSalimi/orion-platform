package orion.catalog.service.infrastructure.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["orion.catalog.service.infrastructure"])
@EntityScan(basePackages = ["orion.catalog.service.infrastructure"])
class DatabaseConfig