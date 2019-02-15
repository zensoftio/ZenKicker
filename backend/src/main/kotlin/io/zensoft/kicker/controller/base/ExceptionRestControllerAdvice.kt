package io.zensoft.kicker.controller.base

import io.zensoft.kicker.domain.exception.ErrorDto
import io.zensoft.kicker.domain.exception.ExceptionResponse
import io.zensoft.kicker.exception.ServiceException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.validation.BindException
import org.springframework.validation.DataBinder
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.MultipartException
import javax.validation.ConstraintViolationException

/**
 * @author Yauheni Efimenko
 */
@RestControllerAdvice
class ExceptionRestControllerAdvice {

    /**
     * In order to get access to private field in PageRequest
     * JSR-303 validated property 'maySortBy' does not have a corresponding accessor for Spring data binding -
     * DataBinder's configuration (bean property versus direct field access)
     */
    @InitBinder
    private fun activateDirectFieldAccess(dataBinder: DataBinder) {
        dataBinder.initDirectFieldAccess()
    }

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

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementExceptionHandler(exception: NoSuchElementException): ExceptionResponse =
            ExceptionResponse(BAD_REQUEST.value(), exception.message!!)

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(MultipartException::class)
    fun multipartExceptionHandler(exception: MultipartException): ExceptionResponse {
        var message = exception.message!!
        if (exception is MaxUploadSizeExceededException) {
            message = "Maximum upload size exceeded"
        }
        return ExceptionResponse(BAD_REQUEST.value(), message)
    }

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationExceptionHandler(exception: ConstraintViolationException): ExceptionResponse =
            ExceptionResponse(BAD_REQUEST.value(), exception.message!!)

    @ResponseStatus(code = INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateExceptionHandler(exception: IllegalStateException): ExceptionResponse =
            ExceptionResponse(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.reasonPhrase)

}