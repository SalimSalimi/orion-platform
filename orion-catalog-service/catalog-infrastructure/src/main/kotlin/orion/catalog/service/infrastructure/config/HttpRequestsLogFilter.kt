package orion.catalog.service.infrastructure.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.system.measureTimeMillis

@Configuration
class HttpRequestsLogFilter: OncePerRequestFilter() {
    companion object {
        private val logger = LoggerFactory.getLogger(HttpRequestsLogFilter::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val method = request.method
        val path = request.requestURI

        val duration = measureTimeMillis {
            filterChain.doFilter(request, response)
        }

        HttpRequestsLogFilter.logger.info(
            "Handled request method={} path={} status={} correlationId={} duration={}ms",
            method,
            path,
            response.status,
            MDC.get(CorrelationIdFilter.CORRELATION_ID_HEADER),
            duration
        )
    }
}