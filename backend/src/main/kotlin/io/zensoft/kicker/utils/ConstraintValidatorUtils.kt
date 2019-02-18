package io.zensoft.kicker.utils

import org.springframework.util.ReflectionUtils
import javax.validation.ConstraintValidatorContext

/**
 * @author Yauheni Efimenko
 */
object ConstraintValidatorUtils {

    fun getValueFromField(clazz: Any, fieldName: String): Any? {
        val field = ReflectionUtils.findField(clazz::class.java, fieldName)
        ReflectionUtils.makeAccessible(field!!)

        return ReflectionUtils.getField(field, clazz)
    }

    fun buildConstraintViolation(context: ConstraintValidatorContext, message: String, fieldName: String) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation()
    }

}