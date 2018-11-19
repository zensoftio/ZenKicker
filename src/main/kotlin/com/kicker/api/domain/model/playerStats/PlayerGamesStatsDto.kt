package com.kicker.api.domain.model.playerStats

import com.kicker.api.domain.model.game.GameDto
import com.kicker.api.model.PlayerStats

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