package orion.catalog.catalog_bff.application.user.impl

import orion.catalog.catalog_bff.application.shared.annotation.Usecase
import orion.catalog.catalog_bff.application.user.GetUserInfoUsecase
import orion.catalog.catalog_bff.application.user.repository.UserRepository
import orion.catalog.catalog_bff.domain.User

@Usecase
class GetUserInfoUsecaseImpl(
    private val userRepository: UserRepository
): GetUserInfoUsecase {

    override fun execute(input: Unit): User {
        return userRepository.getInfo()
    }
}