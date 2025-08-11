package orion.catalog.service.domain.category

import java.util.UUID

class Category(
    val id: UUID,
    var name: String,
    var description: String? = null,
    var active: Boolean = true
) {
    init {
        require(name.isNotBlank()) { "Category name must not be blank" }
    }

    fun activate() {
        active = true
    }

    fun deactivate() {
        active = false
    }

    fun rename(newName: String) {
        require(newName.isNotBlank()) { "Category name must not be blank" }
        name = newName
    }
}