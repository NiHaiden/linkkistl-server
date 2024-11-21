package io.nhaiden.linkkistl.dto

import java.io.Serializable
import java.util.*

/**
 * DTO for {@link io.nhaiden.linkkistl.model.SavedLink}
 */
data class SavedLinkResponse(val linkId: UUID?, val linkUrl: String? = null, val title: String? = null, val description: String? = null, val userId: String? = null) :
    Serializable