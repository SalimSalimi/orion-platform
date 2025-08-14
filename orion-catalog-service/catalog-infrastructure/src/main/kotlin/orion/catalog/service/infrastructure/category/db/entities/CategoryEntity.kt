package orion.catalog.service.infrastructure.category.db.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "category", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
class CategoryEntity(

    @Id
    val id: UUID,

    @Column("name", nullable = false)
    val name: String,

    @Column("description")
    val description: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    var updatedAt: Instant = Instant.now(),
)