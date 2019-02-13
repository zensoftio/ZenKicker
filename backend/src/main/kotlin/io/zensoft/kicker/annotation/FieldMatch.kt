package io.zensoft.kicker.annotation

import io.zensoft.kicker.annotation.validator.FieldMatchValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * @author Yauheni Efimenko
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [FieldMatchValidator::class])
annotation class FieldMatch(
        val first: String,
        val second: String,
        val match: Boolean = true,
        val message: String = "The fields must match",
        val groups: Array<KClass<out Any>> = [],
        val payload: Array<KClass<out Payload>> = []
)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class FieldMatches(val value: Array<FieldMatch> = [])