package io.nhaiden.linkkistl.rest

import io.nhaiden.linkkistl.model.ExternalUser
import io.nhaiden.linkkistl.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
    service: UserService
) {
    @PostMapping("/save-user-id")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUserId(): ResponseEntity<Void> {
        val authentication = SecurityContextHolder.getContext().authentication
        val jwt = authentication.principal as Jwt
        val userId = jwt.getClaim<String>("sub")
        val user = ExternalUser(id = userId)

        val foundUser = userService.findUserById(userId)

        return if (foundUser != null) {
            println("User already exists")
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        } else {
            userService.saveUser(user)
            ResponseEntity.status(HttpStatus.CREATED).build()
        }
    }

}