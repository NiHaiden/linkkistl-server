package io.nhaiden.linkkistl.rest

import io.nhaiden.linkkistl.dto.LinkSaveRequest
import io.nhaiden.linkkistl.dto.SavedLinkResponse
import io.nhaiden.linkkistl.service.LinkService
import io.nhaiden.linkkistl.util.getUserId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/links")
class LinkController(
    private val linkService: LinkService,
) {
    @GetMapping
    fun getAllLinks(): List<SavedLinkResponse> {
        val userId = getUserId()
        return linkService.getAllLinks(userId)
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Link was created!")
    fun saveLink(@RequestBody link: LinkSaveRequest): String {
        linkService.saveLink(link)
        return "Link was created!"
    }

    @GetMapping("/{id}")
    fun getLinkById(@PathVariable id: String): SavedLinkResponse {
        return linkService.getLinkById(UUID.fromString(id))
    }

    @GetMapping("/count")
    fun countLinks(): Long {
        return linkService.countLinks()
    }
}