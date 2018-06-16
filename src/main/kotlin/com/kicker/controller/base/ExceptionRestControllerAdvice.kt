package com.kicker.controller.base

import com.kicker.domain.exception.ErrorDto
import com.kicker.domain.exception.ExceptionResponse
import com.kicker.exception.service.ServiceException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author Yauheni Efimenko
 */
@RestControllerAdvice
class ExceptionRestControllerAdvice {

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(exception: MethodArgumentNotValidException): ExceptionResponse =
            ExceptionResponse(BAD_REQUEST.value(), BAD_REQUEST.reasonPhrase,
                    exception.bindingResult.allErrors.map { ErrorDto(it) })

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun bindExceptionHandler(exception: BindException): ExceptionResponse =
            ExceptionResponse(BAD_REQUEST.value(), BAD_REQUEST.reasonPhrase,
                    exception.bindingResult.allErrors.map { ErrorDto(it) })

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(ServiceException::class)
    fun serviceExceptionHandler(exception: ServiceException): ExceptionResponse =
            ExceptionResponse(BAD_REQUEST.value(), exception.message!!)

    @ResponseStatus(code = NOT_FOUND)
    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementExceptionHandler(exception: NoSuchElementException): ExceptionResponse =
            ExceptionResponse(NOT_FOUND.value(), exception.message!!)

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateExceptionHandler(exception: IllegalStateException): ExceptionResponse =
            ExceptionResponse(BAD_REQUEST.value(), exception.message ?: "Illegal State Exception")

}