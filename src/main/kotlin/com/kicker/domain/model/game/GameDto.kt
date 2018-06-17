package com.kicker.domain.model.game

import com.fasterxml.jackson.annotation.JsonFormat
import com.kicker.model.Game
import java.time.LocalDateTime

/**
 * @author Yauheni Efimenko
 */
data class GameDto(
        val id: Long,
        val redPlayer1Id: Long,
        val redPlayer2Id: Long,
        val yellowPlayer1Id: Long,
        val yellowPlayer2Id: Long,
        val redGoals: Int,
        val yellowGoals: Int,
        @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val date: LocalDateTime,
        val reportedById: Long
) {
    constructor(game: Game) : this(
            game.id,
            game.redPlayer1.id,
            game.redPlayer2.id,
            game.yellowPlayer1.id,
            game.yellowPlayer2.id,
            game.redTeamGoals,
            game.yellowTeamGoals,
            game.date,
            game.reportedBy.id
    )
}