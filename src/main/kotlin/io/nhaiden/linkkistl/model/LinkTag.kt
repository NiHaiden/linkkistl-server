package io.nhaiden.linkkistl.model

import jakarta.persistence.*
import lombok.Builder
import java.util.*

@Entity
@Builder
@Table(name = "link_tags")
open class LinkTag(
    @Column(name = "name")
    open var name: String?,

    @Column(name = "color")
    open var color: String?

) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null


    @ManyToMany
    @JoinTable(
        name = "link_tags_savedLinks",
        joinColumns = [JoinColumn(name = "linkTag_id")],
        inverseJoinColumns = [JoinColumn(name = "savedLinks_id")]
    )
    open var savedLinks: MutableSet<SavedLink> = mutableSetOf()
}