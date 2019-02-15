package io.zensoft.kicker.exception

/**
 * @author Yauheni Efimenko
 */
open class ServiceException(message: String?) : RuntimeException(message)

class DuplicateUsernameException(message: String?) : ServiceException(message)

class PasswordIncorrectException(message: String?) : ServiceException(message)