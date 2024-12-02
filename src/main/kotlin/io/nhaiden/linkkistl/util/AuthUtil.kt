package io.nhaiden.linkkistl.util

import io.nhaiden.linkkistl.exception.UnauthorizedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt


/**
 * Retrieves the user ID from the current security context.
 *
 * @return The user ID as a string.
 * @throws UnauthorizedAccessException if there is no active authentication.
 */
fun getUserId(): String {
    val authentication = SecurityContextHolder.getContext().authentication ?: throw UnauthorizedException("User is not authenticated")
    val jwt = authentication.principal as Jwt
    return jwt.getClaim("sub")
}