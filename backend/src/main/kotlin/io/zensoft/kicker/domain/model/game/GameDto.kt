package io.zensoft.kicker.domain.model.game

import io.zensoft.kicker.domain.model.player.PlayerDto
import io.zensoft.kicker.model.Game
import java.sql.Timestamp

/**
 * @author Yauheni Efimenko
 */
data class GameDto(
        val id: Long,
        val losersGoals: Int,
        val winner1: PlayerDto,
        val winner2: PlayerDto,
        val loser1: PlayerDto,
        val loser2: PlayerDto,
        val reportedBy: PlayerDto,
        val date: Long
) {

    constructor(game: Game) : this(
            game.id,
            game.losersGoals,
            PlayerDto(game.winner1),
            PlayerDto(game.winner2),
            PlayerDto(game.loser1),
            PlayerDto(game.loser2),
            PlayerDto(game.reportedBy),
            Timestamp.valueOf(game.date).time
    )

}