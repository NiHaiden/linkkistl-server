package io.nhaiden.linkkistl.db

import io.nhaiden.linkkistl.model.SavedLink
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface LinkRepository : JpaRepository<SavedLink, UUID>, JpaSpecificationExecutor<SavedLink> {
    @Query("SELECT COUNT(l) FROM SavedLink l WHERE l.user.id = :userId")
    fun countLinks(@Param("userId") userId: String): Long

    @Query("SELECT l FROM SavedLink l WHERE l.user.id = :userId")
    fun findAllByUser(@Param("userId") userId: String): List<SavedLink>
}