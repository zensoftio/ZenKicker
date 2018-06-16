package com.kicker.domain.model.game

import com.fasterxml.jackson.annotation.JsonFormat
import com.kicker.domain.model.player.PlayerDto
import com.kicker.model.Game
import java.time.LocalDateTime

/**
 * @author Yauheni Efimenko
 */
data class GameDto(
        val id: Long,
        val redPlayer1: PlayerDto,
        val redPlayer2: PlayerDto,
        val yellowPlayer1: PlayerDto,
        val yellowPlayer2: PlayerDto,
        val redGoals: Int,
        val yellowGoals: Int,
        @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val date: LocalDateTime,
        val reportedBy: PlayerDto
) {
    constructor(game: Game) : this(
            game.id,
            PlayerDto(game.redPlayer1),
            PlayerDto(game.redPlayer2),
            PlayerDto(game.yellowPlayer1),
            PlayerDto(game.yellowPlayer2),
            game.redTeamGoals,
            game.yellowTeamGoals,
            game.date,
            PlayerDto(game.reportedBy)
    )
}