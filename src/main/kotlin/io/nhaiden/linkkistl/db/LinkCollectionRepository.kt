package io.nhaiden.linkkistl.db

import io.nhaiden.linkkistl.model.LinkCollection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface LinkCollectionRepository : JpaRepository<LinkCollection, UUID>, JpaSpecificationExecutor<LinkCollection> {

    fun findAllByOwningUserId(userId: String): List<LinkCollection>
}