package orion.catalog.catalog_bff.infrastructure.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

@Component
class CorrelationIdFilter: OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val correlationId = request.getHeader("X-Correlation-Id")

        if (correlationId != null && correlationId.isNotEmpty()) {
            MDC.put("X-Correlation-Id", correlationId)
        } else {
            MDC.put("X-Correlation-Id", UUID.randomUUID().toString())
        }

        filterChain.doFilter(request, response)
    }
}