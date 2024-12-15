package io.nhaiden.linkkistl.service

import io.nhaiden.linkkistl.db.LinkRepository
import io.nhaiden.linkkistl.dto.LinkSaveRequest
import io.nhaiden.linkkistl.dto.SavedLinkResponse
import io.nhaiden.linkkistl.model.ExternalUser
import io.nhaiden.linkkistl.model.SavedLink
import io.nhaiden.linkkistl.util.getUserId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class LinkService(@Autowired val linkRepository: LinkRepository, val userService: UserService) {

    fun getAllLinks(userId: String): List<SavedLinkResponse> =
        linkRepository.findAllByUser(userId).map { savedLink: SavedLink? ->
            SavedLinkResponse(
                linkId = savedLink?.id,
                title = savedLink?.title,
                description = savedLink?.description,
                linkUrl = savedLink?.linkUrl,
                userId = savedLink?.user?.id
            )
        }

    fun saveLink(link: LinkSaveRequest) {
        val newSavedLink =
            SavedLink(linkUrl = link.linkUrl, title = link.title, savedAt = Date())
        val userId = getUserId()
        val user: ExternalUser = userService.findUserById(userId)!!
        newSavedLink.user = user
        linkRepository.save(newSavedLink)
    }

    fun countLinks(): Long {
        val userId = getUserId()
        return linkRepository.countLinks(userId)
    }

    fun getLinkById(id: UUID): SavedLinkResponse {
        val userId = getUserId()
        val link: SavedLink = linkRepository.findByIdAndUserId(userId=userId, id=id).orElseThrow()

        return SavedLinkResponse(
            linkUrl = link.linkUrl,
            title = link.title,
            description = link.description,
            linkId = link.id,
            userId = link.user?.id
        )
    }
}