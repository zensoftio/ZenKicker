package io.zensoft.kicker.domain.model.player

import io.zensoft.kicker.annotation.Email
import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
data class UpdatePlayerLoginRequest(
        @field:NotBlank @field:Email var login: String? = null
)