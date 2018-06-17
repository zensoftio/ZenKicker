package com.kicker.annotation.validator

import com.kicker.annotation.FieldMatch
import com.kicker.utils.ProjectUtils
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
        val valueField1 = ProjectUtils.getValueFromField(clazz, firstFieldName)
        val valueField2 = ProjectUtils.getValueFromField(clazz, secondFieldName)

        val condition = when (match) {
            true -> valueField1 == valueField2
            false -> valueField1 != valueField2
        }

        return when (condition) {
            true -> true
            false -> {
                ProjectUtils.buildConstraintViolation(context, message, firstFieldName)
                false
            }
        }
    }

}