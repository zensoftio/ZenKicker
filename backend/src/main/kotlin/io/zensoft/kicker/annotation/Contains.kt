package io.zensoft.kicker.annotation

import io.zensoft.kicker.annotation.validator.ContainsValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * @author Yauheni Efimenko
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [(ContainsValidator::class)])
annotation class Contains(
        val value: String,
        val map: String,
        val message: String = "Collection is not contains value",
        val groups: Array<KClass<out Any>> = [],
        val payload: Array<KClass<out Payload>> = []
)