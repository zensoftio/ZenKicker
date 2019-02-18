package io.zensoft.kicker.domain.model.player

import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
data class UpdatePlayerPasswordRequest(
        @field:NotBlank var currentPassword: String? = null,
        @field:NotBlank var newPassword: String? = null
)