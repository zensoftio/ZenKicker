package io.zensoft.kicker.annotation

import io.zensoft.kicker.annotation.validator.EmailValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * @author Yauheni Efimenko
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [(EmailValidator::class)])
annotation class Email(
        val message: String = "is not valid",
        val groups: Array<KClass<out Any>> = [],
        val payload: Array<KClass<out Payload>> = []
)