package io.nhaiden.linkkistl.service

import io.nhaiden.linkkistl.db.ExternalUserRepository
import io.nhaiden.linkkistl.db.LinkCollectionRepository
import io.nhaiden.linkkistl.db.LinkRepository
import io.nhaiden.linkkistl.dto.CollectionSaveRequest
import io.nhaiden.linkkistl.exception.NotFoundException
import io.nhaiden.linkkistl.mapper.LinkCollectionResponse
import io.nhaiden.linkkistl.mapper.LinkCollectionMapper
import io.nhaiden.linkkistl.model.ExternalUser
import io.nhaiden.linkkistl.model.LinkCollection
import io.nhaiden.linkkistl.model.SavedLink
import io.nhaiden.linkkistl.util.getUserId
import org.springframework.stereotype.Service
import java.util.*

@Service
class CollectionService(
    private val linkCollectionRepository: LinkCollectionRepository,
    private val linkRepository: LinkRepository,
    private val externalUserRepository: ExternalUserRepository
) {
    private val linkCollectionMapper = LinkCollectionMapper()
    fun createCollection(saveRequest: CollectionSaveRequest, userId: String) {
        val linkCollection = LinkCollection(name = saveRequest.name, description = saveRequest.description)
        val owningUser: ExternalUser = externalUserRepository.findById(userId).orElseThrow { NotFoundException("The user for this collection was not found in our records.") }
        linkCollection.owningUser = owningUser
        linkCollectionRepository.save(linkCollection)
    }

    fun getCollectionById(id: String): LinkCollectionResponse {
        val collection = linkCollectionRepository.findById(UUID.fromString(id))
            .orElseThrow { NotFoundException("The collection does not exist!") }

        return linkCollectionMapper.toEssentialDto(collection)
    }

    fun getAllCollections(userId: String): List<LinkCollectionResponse> {
        val collections = linkCollectionRepository.findAllByOwningUserId(userId)
        val mappedCollections: List<LinkCollectionResponse> = collections.map {
            collection -> linkCollectionMapper.toEssentialDto(collection)
        }
        return mappedCollections
    }

    fun addLinkToCollection(linkId: String, collectionId: String) {
        val linkCollection: LinkCollection = linkCollectionRepository.findById(UUID.fromString(collectionId))
            .orElseThrow { NotFoundException("The collection was not found in the database!") }

        val linkToBeAdded: SavedLink = linkRepository.findById(UUID.fromString(linkId))
            .orElseThrow { NotFoundException("The link was not added to the database!") }

        linkCollection.savedLinks.add(linkToBeAdded)
        linkToBeAdded.linkCollections.add(linkCollection)

        linkCollectionRepository.save(linkCollection)
        linkRepository.save(linkToBeAdded)
    }

    /**
     * Counts the number of collections associated with the currently authenticated user.
     *
     * @return The total count of collections belonging to the user.
     * @throws UnauthorizedException if the user is not authenticated.
     */
    fun countCollections(): Long {
        val userId = getUserId()
        return linkCollectionRepository.countCollections(userId)
    }
}