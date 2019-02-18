package io.zensoft.kicker.domain.model.game

import io.zensoft.kicker.annotation.FieldMatch
import io.zensoft.kicker.annotation.FieldMatches
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * @author Yauheni Efimenko
 */
@FieldMatches(value = [
    FieldMatch(first = "loser1Id", second = "loser2Id", match = false, message = "The same player cannot play for the same team"),
    FieldMatch(first = "loser1Id", second = "winner1Id", match = false, message = "The same player can not play for different team"),
    FieldMatch(first = "loser1Id", second = "winner2Id", match = false, message = "The same player can not play for different team"),
    FieldMatch(first = "loser2Id", second = "winner1Id", match = false, message = "The same player can not play for different team"),
    FieldMatch(first = "loser2Id", second = "winner2Id", match = false, message = "The same player can not play for different team"),
    FieldMatch(first = "winner1Id", second = "winner2Id", match = false, message = "The same player can not play for the same team")
])
data class GameRegistrationRequest(
        @field:NotNull var winner1Id: Long? = null,
        @field:NotNull var winner2Id: Long? = null,
        @field:NotNull var loser1Id: Long? = null,
        @field:NotNull var loser2Id: Long? = null,
        @field:NotNull @field:Min(value = 0) @field:Max(9) var losersGoals: Int? = null
)