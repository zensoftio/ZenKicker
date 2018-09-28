package com.kicker.domain.model.game

import com.kicker.domain.model.player_stats.PlayerStatsDto
import com.kicker.model.Game

/**
 * @author Yauheni Efimenko
 */
data class FullGameDto(
        val game: GameDto,
        val playerStatsList: List<PlayerStatsDto>
) {
    constructor(game: Game) : this(
            GameDto(game),
            game.playerStatsList.map { PlayerStatsDto(it) }
    )
}