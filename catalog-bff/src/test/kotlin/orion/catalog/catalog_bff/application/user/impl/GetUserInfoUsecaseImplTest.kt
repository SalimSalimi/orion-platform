package orion.catalog.catalog_bff.application.user.impl

import org.mockito.Mockito
import org.mockito.Mockito.mock
import orion.catalog.catalog_bff.application.user.repository.UserRepository
import orion.catalog.catalog_bff.domain.User
import kotlin.test.Test
import kotlin.test.assertEquals


class GetUserInfoUsecaseImplTest {
    private val userRepository: UserRepository = mock()

    private val useCase: GetUserInfoUsecaseImpl = GetUserInfoUsecaseImpl(userRepository)

    @Test
    fun `should return user informations`() {
        val user = User("test", "test@test.com", "test1", "test2")

        Mockito.`when`(userRepository.getInfo()).thenReturn(user)

        val result = useCase.execute()

        assertEquals(user, result)
    }
}