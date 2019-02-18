package io.zensoft.kicker.domain.model.player

import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
class UpdatePlayerFullNameRequest(
        @field:NotBlank var fullName: String? = null
)