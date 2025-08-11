package orion.catalog.service.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import orion.catalog.service.domain.product.Product
import java.util.UUID

class ProductTest {

    @Test
    fun shouldCreateProductInstance() {
        val product = Product(UUID.randomUUID(), "headphones", "Headphone with USB-C connector", 15.00, 2, setOf())
        assertNotNull(product)
    }
}