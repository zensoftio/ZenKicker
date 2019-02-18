package io.zensoft.kicker.domain.model.player

import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
data class UpdatePlayerUsernameRequest(
        @field:NotBlank var username: String? = null
)