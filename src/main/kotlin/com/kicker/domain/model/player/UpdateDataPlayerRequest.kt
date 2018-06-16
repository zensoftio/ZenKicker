package com.kicker.domain.model.player

import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
data class UpdateDataPlayerRequest(
        @field:NotBlank var username: String? = null,
        @field:NotBlank var firstName: String? = null,
        @field:NotBlank var lastName: String? = null
)