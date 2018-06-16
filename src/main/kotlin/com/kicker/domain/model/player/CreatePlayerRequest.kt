package com.kicker.domain.model.player

import com.kicker.annotation.FieldMatch
import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
@FieldMatch(first = "password", second = "retypePassword", message = "The password fields must match")
data class CreatePlayerRequest(
        @field:NotBlank var username: String? = null,
        @field:NotBlank var firstName: String? = null,
        @field:NotBlank var lastName: String? = null,
        @field:NotBlank var password: String? = null,
        @field:NotBlank var retypePassword: String? = null
)