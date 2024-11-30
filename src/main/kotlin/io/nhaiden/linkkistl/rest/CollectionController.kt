package io.nhaiden.linkkistl.rest

import io.nhaiden.linkkistl.dto.CollectionSaveRequest
import io.nhaiden.linkkistl.mapper.LinkCollectionResponse
import io.nhaiden.linkkistl.service.CollectionService
import io.nhaiden.linkkistl.util.getUserId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/collections")
class CollectionController(private val collectionService: CollectionService) {
    @GetMapping
    fun getAllCollections(): List<LinkCollectionResponse> {
        val userId = getUserId()

        return collectionService.getAllCollections(userId)
    }

    @GetMapping("/{id}")
    fun getCollectionById(@PathVariable id: String): LinkCollectionResponse {
        return collectionService.getCollectionById(id)
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "The collection was created!")
    fun createCollection(@RequestBody collection: CollectionSaveRequest) {
        val userId = getUserId()
        collectionService.createCollection(collection, userId)
    }

    @GetMapping("/count")
    fun getCount(): Long {
        collectionService.countCollections();
    }
}