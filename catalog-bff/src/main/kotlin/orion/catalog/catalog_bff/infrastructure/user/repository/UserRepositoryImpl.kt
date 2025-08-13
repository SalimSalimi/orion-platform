package orion.catalog.catalog_bff.infrastructure.user.repository

import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Repository
import orion.catalog.catalog_bff.application.user.repository.UserRepository
import orion.catalog.catalog_bff.domain.User
import orion.catalog.catalog_bff.infrastructure.user.services.AuthenticationService

@Repository
class UserRepositoryImpl(
    private val authenticationService: AuthenticationService
): UserRepository {

    override fun getInfo(): User {
        val oidcUser: OidcUser = authenticationService.getAuthentificationUserInformation()
        return User(
            oidcUser.givenName,
            oidcUser.email,
            oidcUser.name,
            oidcUser.familyName
        )
    }
}