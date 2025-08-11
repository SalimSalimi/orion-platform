package orion.catalog.service.domain.product

import java.util.UUID

class Product(
    val uuid: UUID,
    var name: String,
    var description: String?,
    var price: Double,
    var availableStock: Int
) {
    init {
        require(name.isNotBlank()) { "Product name must not be blank" }
        require(price >= 0) { "Price must be non-negative" }
        require(availableStock >= 0) { "Available stock cannot be negative" }
    }

    fun updateStock(quantity: Int) {
        val newStock = availableStock + quantity
        require(newStock >= 0) { "Stock cannot be negative" }
        availableStock = newStock
    }

    fun changePrice(newPrice: Double) {
        require(newPrice >= 0) { "Price must be non-negative" }
        price = newPrice
    }
}