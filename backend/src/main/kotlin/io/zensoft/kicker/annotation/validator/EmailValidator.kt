package io.zensoft.kicker.annotation.validator

import io.zensoft.kicker.annotation.Email
import io.zensoft.kicker.config.property.DomainProperties
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * @author Yauheni Efimenko
 */
@Component
class EmailValidator(domainProperties: DomainProperties) : ConstraintValidator<Email, String> {

    private val regex = "^([a-zA-Z0-9_.-]+)@(${domainProperties.domains.joinToString("|")})$".toRegex()


    override fun isValid(email: String, context: ConstraintValidatorContext): Boolean {
        if (!email.matches(regex)) {
            return false
        }

        return true
    }

}