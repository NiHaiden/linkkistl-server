package io.nhaiden.linkkistl.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import lombok.Builder
import java.util.*

@Entity
@Table(name = "savedlinks")
@Builder
open class SavedLink(
    @Column(name = "linkurl")
    @NotBlank
    open var linkUrl: String? = null,

    @Column(name = "title")
    @NotBlank
    open var title: String? = null,

    @Column(name = "description")
    @NotBlank
    open var description: String? = null,

    @Column(name = "savedAt")
    @PastOrPresent
    open var savedAt: Date? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    open var user: ExternalUser? = null

    @ManyToMany(mappedBy = "savedLinks")
    open var linkCollections: MutableSet<LinkCollection> = mutableSetOf()

    @ManyToMany(mappedBy = "savedLinks")
    open var linkTags: MutableSet<LinkTag> = mutableSetOf()
}