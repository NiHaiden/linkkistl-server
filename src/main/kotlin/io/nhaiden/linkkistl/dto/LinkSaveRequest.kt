package io.nhaiden.linkkistl.dto

import java.io.Serializable

/**
 * DTO for {@link io.nhaiden.linkkistl.model.SavedLink}
 */
data class LinkSaveRequest(
    val linkUrl: String,
    val title: String,
    val description: String,
) : Serializable