package io.zensoft.kicker.exception.controller

/**
 * @author Yauheni Efimenko
 */
open class ControllerException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}

class MultipartFileException : ControllerException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}