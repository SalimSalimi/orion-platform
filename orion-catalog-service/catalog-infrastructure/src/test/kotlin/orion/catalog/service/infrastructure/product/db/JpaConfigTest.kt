package orion.catalog.service.infrastructure.product.db

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan("orion.catalog.service.infrastructure.product.db")
@EnableJpaRepositories("orion.catalog.service.infrastructure.product.db")
@EnableAutoConfiguration
class JpaConfigTest
