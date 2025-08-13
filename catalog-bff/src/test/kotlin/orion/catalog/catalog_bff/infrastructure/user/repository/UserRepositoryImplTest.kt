package orion.catalog.catalog_bff.infrastructure.user.repository

import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UserRepositoryImplTest {
    private val oidcUser: OidcUser = mock()

    private val repository = UserRepositoryImpl(oidcUser)

    @Test
    fun `should return user informations`() {
        Mockito.`when`(oidcUser.name).thenReturn("name")
        Mockito.`when`(oidcUser.email).thenReturn("email")
        Mockito.`when`(oidcUser.familyName).thenReturn("familyName")
        Mockito.`when`(oidcUser.givenName).thenReturn("givenName")

        val user = repository.getInfo()

        assertNotNull(user)
        assertEquals(user.email, "email")
        assertEquals(user.username, "givenName")
        assertEquals(user.firstName, "name")
        assertEquals(user.lastName, "familyName")
    }
}