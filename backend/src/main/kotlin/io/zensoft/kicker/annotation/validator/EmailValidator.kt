package io.zensoft.kicker.annotation.validator

import io.zensoft.kicker.annotation.Email
import io.zensoft.kicker.config.property.DomainProperties
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * @author Yauheni Efimenko
 */
class EmailValidator(domainProperties: DomainProperties) : ConstraintValidator<Email, String> {

    private val regex: Regex by lazy {
        if (domainProperties.domains.isEmpty()) {
            ".+".toRegex()
        } else {
            ".+@${domainProperties.domains.joinToString("|", "(", ")")}$".toRegex()
        }
    }


    override fun isValid(email: String?, context: ConstraintValidatorContext): Boolean =
            email?.matches(regex) ?: false

}