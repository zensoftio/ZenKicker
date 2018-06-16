package com.kicker.controller.base

import com.kicker.domain.exception.ErrorDto
import com.kicker.domain.exception.ExceptionResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus.*
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

//    @ResponseStatus(code = BAD_REQUEST)
//    @ExceptionHandler(BindException::class)
//    fun bindExceptionHandler(exception: BindException): ExceptionResponse =
//            ExceptionResponse(BAD_REQUEST.value(), BAD_REQUEST.reasonPhrase,
//                    exception.bindingResult.allErrors.map { ErrorDto(it) })

    @ResponseStatus(code = CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun dataIntegrityViolationExceptionHandler(exception: DataIntegrityViolationException): ExceptionResponse =
            ExceptionResponse(CONFLICT.value(), exception.message ?: "Data Integrity Violation")

    @ResponseStatus(code = NOT_FOUND)
    @ExceptionHandler(NoSuchElementException::class)
    fun dataIntegrityViolationExceptionHandler(exception: NoSuchElementException): ExceptionResponse =
            ExceptionResponse(NOT_FOUND.value(), exception.message!!)

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateExceptionHandler(exception: IllegalStateException): ExceptionResponse =
            ExceptionResponse(BAD_REQUEST.value(), exception.message ?: "Illegal State Exception")

}