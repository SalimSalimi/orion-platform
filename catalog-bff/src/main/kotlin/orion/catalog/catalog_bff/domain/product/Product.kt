package orion.catalog.catalog_bff.domain.product

class Product(
    val name: String,
    val price: Double,
    val stock: Int,
    val categoriesId: Set<String>
)