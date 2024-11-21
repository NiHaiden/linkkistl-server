package io.nhaiden.linkkistl.service

import io.nhaiden.linkkistl.db.ExternalUserRepository
import io.nhaiden.linkkistl.model.ExternalUser
import org.springframework.stereotype.Service

@Service
class UserService(
    private val externalUserRepository: ExternalUserRepository,
    userRepository: ExternalUserRepository
) {
    fun saveUser(user: ExternalUser) {
        try {
            externalUserRepository.save(user)
        } catch (e: Exception) {
            println("Error saving user: $e")
        }
    }

    fun findUserById(userId: String): ExternalUser? {
        return externalUserRepository.findById(userId).orElse(null)
    }
}