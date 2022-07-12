package com.example.rupikz.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.util.*
import javax.validation.ValidationException

class CityNotFoundException : RuntimeException("City not found")
class CityAlreadyExistException : RuntimeException("City already exist")

class TemperatureNotFoundException : RuntimeException("Temperature not found")
class TemperatureAlreadyExistException : RuntimeException("Temperature already exist")

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationException(e: ValidationException, request: WebRequest): ErrorMessage {
        return errorMessage(e, HttpStatus.BAD_REQUEST, request.contextPath)
    }

    @ExceptionHandler(value = [
        CityNotFoundException::class,
        TemperatureNotFoundException::class
    ])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(e: RuntimeException, request: WebRequest): ErrorMessage {
        return errorMessage(e, HttpStatus.NOT_FOUND, request.contextPath)
    }

    @ExceptionHandler(value = [
        CityAlreadyExistException::class,
        TemperatureAlreadyExistException::class
    ])
    @ResponseStatus(HttpStatus.CONFLICT)
    fun alreadyExistException(e: RuntimeException, request: WebRequest): ErrorMessage {
        return errorMessage(e, HttpStatus.CONFLICT, request.contextPath)
    }

    @ExceptionHandler(value = [Exception::class])
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun handleOtherException(e: Exception, request: WebRequest): ErrorMessage {
        return errorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR, request.contextPath)
    }

    fun errorMessage(exception: Throwable, status: HttpStatus, path: String) = ErrorMessage(
        timestamp = Calendar.getInstance().time,
        status = status.value(),
        message = exception.message,
        error = exception.cause?.message
    )

    data class ErrorMessage(val timestamp: Date, val status: Int, val message: String?, val error: String?)
}
