package io.nhaiden.linkkistl.db

import io.nhaiden.linkkistl.model.ExternalUser
import io.nhaiden.linkkistl.model.SavedLink
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ExternalUserRepository : JpaRepository<ExternalUser, String>, JpaSpecificationExecutor<SavedLink> {
}