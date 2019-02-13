package io.zensoft.kicker.annotation.validator

import io.zensoft.kicker.annotation.Contains
import io.zensoft.kicker.utils.ConstraintValidatorUtils
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * @author Yauheni Efimenko
 */
class ContainsValidator : ConstraintValidator<Contains, Any> {

    private lateinit var valueFieldName: String
    private lateinit var setFieldName: String
    private lateinit var message: String


    override fun initialize(constraintAnnotation: Contains) {
        valueFieldName = constraintAnnotation.value
        setFieldName = constraintAnnotation.set
        message = constraintAnnotation.message
    }

    override fun isValid(clazz: Any, context: ConstraintValidatorContext): Boolean {
        val value = ConstraintValidatorUtils.getValueFromField(clazz, valueFieldName)
        val set = ConstraintValidatorUtils.getValueFromField(clazz, setFieldName) as Set<*>

        val condition = set.contains(value)

        return when (condition) {
            true -> true
            false -> {
                ConstraintValidatorUtils.buildConstraintViolation(context, message, setFieldName)
                false
            }
        }
    }

}
