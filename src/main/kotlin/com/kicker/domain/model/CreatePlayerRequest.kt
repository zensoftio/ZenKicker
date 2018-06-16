package com.kicker.domain.model

import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
data class CreatePlayerRequest(
        @field:NotBlank var username: String? = null,
        @field:NotBlank var firstName: String? = null,
        @field:NotBlank var lastName: String? = null
)