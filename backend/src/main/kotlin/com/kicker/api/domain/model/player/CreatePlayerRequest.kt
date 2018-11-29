package com.kicker.api.domain.model.player

import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
data class CreatePlayerRequest(
        @field:NotBlank var username: String? = null,
        @field:NotBlank var password: String? = null
)