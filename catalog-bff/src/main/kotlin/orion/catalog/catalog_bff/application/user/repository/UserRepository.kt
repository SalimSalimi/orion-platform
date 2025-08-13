package orion.catalog.catalog_bff.application.user.repository

import orion.catalog.catalog_bff.domain.User

interface UserRepository {

    fun getInfo(): User
}