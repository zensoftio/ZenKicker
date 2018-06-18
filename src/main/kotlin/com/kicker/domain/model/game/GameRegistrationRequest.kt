package com.kicker.domain.model.game

import com.kicker.annotation.FieldMatch
import com.kicker.annotation.FieldMatches
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */
@FieldMatches(value = [
    FieldMatch(first = "redGoals", second = "yellowGoals", match = false, message = "Game score cannot be equal"),
    FieldMatch(first = "redPlayer1", second = "redPlayer2", match = false, message = "The same player cannot play for the same team"),
    FieldMatch(first = "redPlayer1", second = "yellowPlayer1", match = false, message = "The same player can not play for different team"),
    FieldMatch(first = "redPlayer1", second = "yellowPlayer2", match = false, message = "The same player can not play for different team"),
    FieldMatch(first = "redPlayer2", second = "yellowPlayer1", match = false, message = "The same player can not play for different team"),
    FieldMatch(first = "redPlayer2", second = "yellowPlayer2", match = false, message = "The same player can not play for different team"),
    FieldMatch(first = "yellowPlayer1", second = "yellowPlayer2", match = false, message = "The same player can not play for the same team")
])
data class GameRegistrationRequest(
        @field:NotBlank var redPlayer1: String? = null,
        @field:NotBlank var redPlayer2: String? = null,
        @field:NotBlank var yellowPlayer1: String? = null,
        @field:NotBlank var yellowPlayer2: String? = null,
        @field:Min(value = 0) @field:Max(10) var redGoals: Int? = null,
        @field:Min(value = 0) @field:Max(10) var yellowGoals: Int? = null
)