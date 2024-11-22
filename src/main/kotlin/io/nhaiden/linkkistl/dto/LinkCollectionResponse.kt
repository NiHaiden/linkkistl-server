package io.nhaiden.linkkistl.mapper

import io.nhaiden.linkkistl.dto.SavedLinkResponse
import io.nhaiden.linkkistl.model.VisibilityStatus
import java.util.*

data class LinkCollectionResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val visibilityStatus: VisibilityStatus,
    val linkCount: Int,
    val savedLinks: List<SavedLinkResponse>
)