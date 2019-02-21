package io.zensoft.kicker.exception

/**
 * @author Yauheni Efimenko
 */
open class ServiceException(message: String?) : RuntimeException(message)

class DuplicateEmailException(message: String?) : ServiceException(message)

class PasswordIncorrectException(message: String?) : ServiceException(message)