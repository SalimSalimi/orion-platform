package orion.catalog.service.infrastructure.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

@Component
class CorrelationIdFilter: OncePerRequestFilter() {

    companion object {
        const val CORRELATION_ID_HEADER = "X-Correlation-Id"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val correlationId = request.getHeader(CORRELATION_ID_HEADER)

        if (correlationId != null && correlationId.isNotEmpty()) {
            MDC.put(CORRELATION_ID_HEADER, correlationId)
        } else {
            MDC.put(CORRELATION_ID_HEADER, UUID.randomUUID().toString())
        }

        filterChain.doFilter(request, response)
    }
}