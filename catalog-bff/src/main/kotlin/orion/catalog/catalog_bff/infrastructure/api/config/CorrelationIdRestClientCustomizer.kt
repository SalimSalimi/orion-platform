package orion.catalog.catalog_bff.infrastructure.api.config

import org.slf4j.MDC
import org.springframework.boot.web.client.RestClientCustomizer
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import orion.catalog.catalog_bff.infrastructure.config.CorrelationIdFilter
import java.util.UUID

@Component
class CorrelationIdRestClientCustomizer: RestClientCustomizer {

    override fun customize(restClientBuilder: RestClient.Builder?) {
        restClientBuilder?.requestInterceptor { request, body, execution ->
            val correlationId = MDC.get(CorrelationIdFilter.CORRELATION_ID_HEADER) ?: UUID.randomUUID().toString()
            request.headers.add(CorrelationIdFilter.CORRELATION_ID_HEADER, correlationId)
            execution.execute(request, body)
        }
    }
}