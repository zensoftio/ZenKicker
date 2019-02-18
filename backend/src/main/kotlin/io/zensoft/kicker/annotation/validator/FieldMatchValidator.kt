package io.zensoft.kicker.annotation.validator

import io.zensoft.kicker.annotation.FieldMatch
import io.zensoft.kicker.utils.ConstraintValidatorUtils
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * @author Yauheni Efimenko
 */
class FieldMatchValidator : ConstraintValidator<FieldMatch, Any> {

    private lateinit var firstFieldName: String
    private lateinit var secondFieldName: String
    private lateinit var message: String
    private var match: Boolean = true


    override fun initialize(constraintAnnotation: FieldMatch) {
        firstFieldName = constraintAnnotation.first
        secondFieldName = constraintAnnotation.second
        message = constraintAnnotation.message
        match = constraintAnnotation.match
    }

    override fun isValid(clazz: Any, context: ConstraintValidatorContext): Boolean {
        val valueField1 = ConstraintValidatorUtils.getValueFromField(clazz, firstFieldName)
        val valueField2 = ConstraintValidatorUtils.getValueFromField(clazz, secondFieldName)

        val condition = when (match) {
            true -> valueField1 == valueField2
            false -> valueField1 != valueField2
        }

        return when (condition) {
            true -> true
            false -> {
                ConstraintValidatorUtils.buildConstraintViolation(context, message, firstFieldName)
                false
            }
        }
    }

}