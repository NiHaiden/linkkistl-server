package io.nhaiden.linkkistl.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt


fun getUserId(): String {
    val authentication = SecurityContextHolder.getContext().authentication
    val jwt = authentication.principal as Jwt
    return jwt.getClaim("sub")
}