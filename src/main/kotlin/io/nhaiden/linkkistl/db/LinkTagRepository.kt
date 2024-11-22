package io.nhaiden.linkkistl.db

import io.nhaiden.linkkistl.model.LinkTag
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LinkTagRepository : JpaRepository<LinkTag, UUID> {
}