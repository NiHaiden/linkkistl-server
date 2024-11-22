package io.nhaiden.linkkistl.model

import jakarta.persistence.*
import lombok.Builder

@Entity
@Table(name = "external_user")
@Builder
open class ExternalUser(
    @Column(name = "profileImageUrl")
    open var profileImageUrl: String? = null,

    @Id
    @Column(name = "id", nullable = false)
    open var id: String? = null
) {

}