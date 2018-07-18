package com.kicker.domain.model.game

import com.fasterxml.jackson.annotation.JsonFormat
import com.kicker.model.Game
import java.time.LocalDateTime

/**
 * @author Yauheni Efimenko
 */
data class GameDto(
        val id: Long,
        @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val date: LocalDateTime,
        val reportedById: Long,
        val score: String
) {
    constructor(game: Game) : this(
            game.id,
            game.date,
            game.reportedBy.id,
            game.score
    )
}