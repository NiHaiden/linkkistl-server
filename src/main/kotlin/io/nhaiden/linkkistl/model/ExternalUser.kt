package io.nhaiden.linkkistl.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "external_user")
open class ExternalUser(
    @Column(name = "profileImageUrl")
    open var profileImageUrl: String? = null,

    @Id
    @Column(name = "id", nullable = false)
    open var id: String? = null
)