package com.example.rupikz.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException


class CityNotFoundException : RuntimeException("City not found")

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(e: CityNotFoundException): ErrorMessage {
        return errorMessage(e, "city_not_found")
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationException(e: ConstraintViolationException): ErrorMessage {
        return errorMessage(e, "validation_failed")
    }

    fun errorMessage(exception: Throwable, code: String) = ErrorMessage(exception.message ?: "", code)

    data class ErrorMessage(val message: String, val code: String)
}