package orion.catalog.catalog_bff.infrastructure.api.config

import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestClientCustomizer
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant

@Component
class RestClientLoggerRequestCustomizer: RestClientCustomizer {

    companion object {
        private val logger = LoggerFactory.getLogger(RestClientLoggerRequestCustomizer::class.java)
    }

    override fun customize(restClientBuilder: RestClient.Builder?) {
        restClientBuilder?.requestInterceptor { request, body, execution ->

            val start = Instant.now()
            logger.info("➡️ Outgoing request: {} {} | Headers: {}",
                request.method, request.uri, request.headers)

            if (body.isNotEmpty()) {
                val bodyString = String(body, StandardCharsets.UTF_8)
                logger.debug("Request body: {}", bodyString)
            }

            val response = execution.execute(request, body)
            val duration = Duration.between(start, Instant.now()).toMillis()

            logger.info("⬅️ Response from {} {}: status={} ({} ms)",
                request.method, request.uri, response.statusCode, duration)

            response
        }
    }
}