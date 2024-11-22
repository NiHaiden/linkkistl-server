package io.nhaiden.linkkistl.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "linkCollection")
open class LinkCollection(
    @Column
    open var name: String = "",

    @Column
    open var description: String = "",

) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "external_user_id")
    open var owningUser: ExternalUser? = null

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    open var createdDate: LocalDateTime = LocalDateTime.now()

    @Column
    @UpdateTimestamp
    open var lastModifiedDate: LocalDateTime = LocalDateTime.now()

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    open var visibilityStatus: VisibilityStatus = VisibilityStatus.PRIVATE

    @ManyToMany
    @JoinTable(
        name = "linkCollection_savedLinks",
        joinColumns = [JoinColumn(name = "linkCollection_id")],
        inverseJoinColumns = [JoinColumn(name = "savedLinks_id")]
    )
    open var savedLinks: MutableSet<SavedLink> = mutableSetOf()
}

enum class VisibilityStatus {
    PUBLIC,
    PRIVATE
}