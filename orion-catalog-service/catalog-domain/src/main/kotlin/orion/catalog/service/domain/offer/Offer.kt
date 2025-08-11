package orion.catalog.service.domain.offer

import java.time.Instant
import java.util.UUID

class Offer(
    val id: UUID,
    var name: String,
    val productId: UUID? = null,
    val categoryId: UUID? = null,
    var discountType: DiscountType,
    var discountValue: Double,
    var validFrom: Instant,
    var validUntil: Instant,
    var exclusive: Boolean = false,
    var stackable: Boolean = true,
    var minPurchaseQuantity: Int = 1,
    var active: Boolean = true
) {
    init {
        require(name.isNotBlank()) { "Offer name must not be blank" }
        require(
            (productId == null) != (categoryId == null)
        ) { "Offer must apply to either a product or a category, not both or none." }
        require(discountValue >= 0) { "Discount value must be non-negative" }
        require(validUntil.isAfter(validFrom)) { "validUntil must be after validFrom" }
        require(minPurchaseQuantity >= 1) { "minPurchaseQuantity must be at least 1" }
    }

    fun activate() {
        active = true
    }

    fun deactivate() {
        active = false
    }

    fun isValidAt(time: Instant): Boolean {
        return active && !time.isBefore(validFrom) && !time.isAfter(validUntil)
    }

    fun rename(newName: String) {
        require(newName.isNotBlank()) { "Offer name must not be blank" }
        name = newName
    }

}