package orion.catalog.catalog_bff.infrastructure.user.repository

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Repository
import orion.catalog.catalog_bff.application.user.repository.UserRepository
import orion.catalog.catalog_bff.domain.User

@Repository
class UserRepositoryImpl(
    @AuthenticationPrincipal private val oidcUser: OidcUser
): UserRepository {

    override fun getInfo(): User {
        return User(
            oidcUser.givenName,
            oidcUser.email,
            oidcUser.name,
            oidcUser.familyName
        )
    }
}