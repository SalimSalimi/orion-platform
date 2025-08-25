package orion.catalog.service.infrastructure.product.db.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import orion.catalog.service.infrastructure.category.db.entities.CategoryEntity
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "products", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
class ProductEntity(
    @Id
    val id: UUID,

    @Column(nullable = false)
    var name: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    @Column(nullable = false)
    var price: Double,

    @Column(nullable = false)
    var stock: Int,

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    var updatedAt: Instant = Instant.now(),

    @OneToMany
    @JoinColumn(name = "category_id")
    var categories: MutableSet<CategoryEntity>? = HashSet()
)