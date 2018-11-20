package com.kicker.api.annotation.validator

import com.kicker.api.annotation.Contains
import com.kicker.api.utils.ConstraintValidatorUtils
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * @author Yauheni Efimenko
 */
class ContainsValidator : ConstraintValidator<Contains, Any> {

    private lateinit var valueFieldName: String
    private lateinit var mapFieldName: String
    private lateinit var message: String


    override fun initialize(constraintAnnotation: Contains) {
        valueFieldName = constraintAnnotation.value
        mapFieldName = constraintAnnotation.map
        message = constraintAnnotation.message
    }

    override fun isValid(clazz: Any, context: ConstraintValidatorContext): Boolean {
        val value = ConstraintValidatorUtils.getValueFromField(clazz, valueFieldName)
        val map = ConstraintValidatorUtils.getValueFromField(clazz, mapFieldName) as Map<*, *>

        val condition = map.contains(value)

        return when (condition) {
            true -> true
            false -> {
                ConstraintValidatorUtils.buildConstraintViolation(context, message, mapFieldName)
                false
            }
        }
    }

}