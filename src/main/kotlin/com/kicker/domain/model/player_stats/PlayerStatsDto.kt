package com.kicker.domain.model.player_stats

import com.kicker.domain.model.player.PlayerDto
import com.kicker.model.PlayerStats

/**
 * @author Yauheni Efimenko
 */
data class PlayerStatsDto(
        val playerDto: PlayerDto,
        val won: Boolean,
        val delta: Double
) {
    constructor(playerStats: PlayerStats) : this(
            PlayerDto(playerStats.player),
            playerStats.won,
            playerStats.delta
    )
}