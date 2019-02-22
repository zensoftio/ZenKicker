package io.zensoft.kicker.domain.model.player

import io.zensoft.kicker.annotation.Email

/**
 * @author Yauheni Efimenko
 */
data class UpdatePlayerEmailRequest(
        @field:Email var email: String? = null
)