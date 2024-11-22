package io.nhaiden.linkkistl.mapper

import io.nhaiden.linkkistl.dto.SavedLinkResponse
import io.nhaiden.linkkistl.model.LinkCollection

class LinkCollectionMapper {

    fun toEssentialDto(linkCollection: LinkCollection): LinkCollectionResponse {
        val savedLinks = linkCollection.savedLinks.map { savedLink ->
            SavedLinkResponse(
                linkId = savedLink.id,
                linkUrl = savedLink.linkUrl,
                title = savedLink.title,
                description = savedLink.description,
                userId = savedLink.user?.id.toString()
            )
        }
        return LinkCollectionResponse(
            id = linkCollection.id!!,
            name = linkCollection.name,
            description = linkCollection.description,
            visibilityStatus = linkCollection.visibilityStatus,
            linkCount = savedLinks.size,
            savedLinks = savedLinks
        )
    }
}