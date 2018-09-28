package com.kicker.domain.model.game

import com.fasterxml.jackson.annotation.JsonFormat
import com.kicker.model.Game
import java.time.LocalDateTime

/**
 * @author Yauheni Efimenko
 */
data class GameDto(
        val id: Long,
        val losersGoals: Int,
        val winner1Id: Long,
        val winner2Id: Long,
        val loser1Id: Long,
        val loser2Id: Long,
        val reportedById: Long,
        @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val date: LocalDateTime
) {

    constructor(game: Game) : this(
            game.id,
            game.losersGoals,
            game.winner1.id,
            game.winner2.id,
            game.loser1.id,
            game.loser2.id,
            game.reportedBy.id,
            game.date
    )

}