package com.denisolek.fleet.infrastructure

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlerAdvice {
    var ex: java.lang.Exception? = null
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<*> {
        ex = e
        return ResponseEntity.status(e.httpStatus).body(e.body)
    }
}

open class Exception(
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val body: ResponseTemplate

) : RuntimeException(body.message) {
    constructor(httpStatus: HttpStatus, body: String) : this(httpStatus, ResponseTemplate(httpStatus.toString(), body))
}

data class ResponseTemplate(var code: String, var message: String)