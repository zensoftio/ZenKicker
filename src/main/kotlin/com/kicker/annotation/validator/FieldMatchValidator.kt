package com.kicker.annotation.validator

import com.kicker.annotation.FieldMatch
import org.springframework.util.ReflectionUtils
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
        val valueField1 = getValue(clazz, firstFieldName)
        val valueField2 = getValue(clazz, secondFieldName)

        val condition = when (match) {
            true -> valueField1 == valueField2
            false -> valueField1 != valueField2
        }

        if (condition) {
            return true
        }

        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(firstFieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation()

        return false
    }

    private fun getValue(clazz: Any, fieldName: String): Any? {
        val field = ReflectionUtils.findField(clazz::class.java, fieldName)
        field!!.isAccessible = true

        return field.get(clazz)
    }

}