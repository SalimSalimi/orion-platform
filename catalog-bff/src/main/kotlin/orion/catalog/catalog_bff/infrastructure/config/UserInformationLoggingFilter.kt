package orion.catalog.catalog_bff.infrastructure.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class UserInformationLoggingFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication != null && authentication.isAuthenticated) {
                val userId = when(val principal = authentication.principal) {
                    is DefaultOidcUser -> principal.email
                    else -> principal.toString()
                }
                MDC.put("user.id", userId)
            }
            filterChain.doFilter(request, response)
        } finally {
            MDC.clear()
        }
    }
}