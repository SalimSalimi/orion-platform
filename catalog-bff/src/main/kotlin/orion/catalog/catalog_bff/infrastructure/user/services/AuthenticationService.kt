package orion.catalog.catalog_bff.infrastructure.user.services

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Service

@Service
class AuthenticationService {

    fun getAuthentificationUserInformation(): OidcUser {
        return SecurityContextHolder.getContext().authentication.principal as OidcUser
    }
}