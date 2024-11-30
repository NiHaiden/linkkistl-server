package io.nhaiden.linkkistl.exception

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("There was an error trying to process the request.")
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleBadRequest(ex: DataIntegrityViolationException?): ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Please check if your request data is correct.")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Please check your request data is correct, some fields might be missing or are incorrect.")
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: UnauthorizedException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized error.")
    }

}