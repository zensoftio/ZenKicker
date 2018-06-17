package com.kicker.annotation.validator

import com.kicker.annotation.Contains
import com.kicker.utils.ProjectUtils
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * @author Ruslan Molchanov
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
        val value = ProjectUtils.getValueFromField(clazz, valueFieldName)
        val map = ProjectUtils.getValueFromField(clazz, mapFieldName) as Map<*, *>

        val condition = map.contains(value)

        return when (condition) {
            true -> true
            false -> {
                ProjectUtils.buildConstraintViolation(context, message, mapFieldName)
                false
            }
        }
    }

}
