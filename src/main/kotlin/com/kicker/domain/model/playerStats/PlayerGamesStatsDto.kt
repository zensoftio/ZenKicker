package com.kicker.domain.model.playerStats

import com.kicker.domain.model.game.GameDto
import com.kicker.model.PlayerStats

/**
 * @author Yauheni Efimenko
 */
data class PlayerGamesStatsDto(
        val gameDto: GameDto,
        val won: Boolean,
        val delta: Int
) {

    constructor(playerStats: PlayerStats) : this(
            GameDto(playerStats.game),
            playerStats.won,
            playerStats.delta.toInt()
    )

}