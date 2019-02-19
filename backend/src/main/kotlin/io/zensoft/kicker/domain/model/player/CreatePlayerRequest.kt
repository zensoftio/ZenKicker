package io.zensoft.kicker.domain.model.player

import io.zensoft.kicker.annotation.Email
import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
data class CreatePlayerRequest(
        @field:NotBlank @field:Email var login: String? = null,
        @field:NotBlank var fullName: String? = null,
        @field:NotBlank var password: String? = null
)