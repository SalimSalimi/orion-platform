package orion.catalog.catalog_bff.presentation.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import orion.catalog.catalog_bff.application.user.GetUserInfoUsecase
import orion.catalog.catalog_bff.domain.User

@RestController
@RequestMapping("/user")
class UserController(
    private val getUserInfoUsecase: GetUserInfoUsecase
) {

    @GetMapping
    fun getUserInfo(): ResponseEntity<User> {
        return ResponseEntity.ok(getUserInfoUsecase.execute())
    }
}
