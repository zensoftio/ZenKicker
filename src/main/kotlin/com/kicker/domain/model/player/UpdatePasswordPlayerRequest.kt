package com.kicker.domain.model.player

import com.kicker.annotation.FieldMatch
import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
@FieldMatch(first = "newPassword", second = "retypePassword", message = "The password fields must match")
data class UpdatePasswordPlayerRequest(
        @field:NotBlank var currentPassword: String? = null,
        @field:NotBlank var newPassword: String? = null,
        @field:NotBlank var retypePassword: String? = null
)