package orion.catalog.catalog_bff.presentation.user

import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import orion.catalog.catalog_bff.application.user.GetUserInfoUsecase
import orion.catalog.catalog_bff.domain.User
import kotlin.test.Test

@WebMvcTest(UserController::class)
class UserControllerIntegrationTest(
    @Autowired private val mockMvc: MockMvc
) {
    @MockitoBean
    lateinit var getUseCaseInfoUsecase: GetUserInfoUsecase

    @Test
    @WithMockUser
    fun `get user informations and return 200 OK`() {
        val user = User("username", "email", "firstName", "lastName")

        Mockito.`when`(getUseCaseInfoUsecase.execute()).thenReturn(user)

        mockMvc.perform(get("/user"))
            .andExpect { status().isOk }
            .andExpect { jsonPath("$.username").value("username") }
            .andExpect { jsonPath("$.email").value("email") }
            .andExpect { jsonPath("$.firstName").value("firstName") }
            .andExpect { jsonPath("$.lastName").value("lastName") }
    }
}