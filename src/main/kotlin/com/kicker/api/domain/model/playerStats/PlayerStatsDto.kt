package com.kicker.api.domain.model.playerStats

import com.kicker.domain.model.player.PlayerDto

/**
 * @author Yauheni Efimenko
 */

data class PlayerStatsDto(
        val player: PlayerDto,
        val countLosses: Long,
        val countWins: Long,
        val goalsAgainst: Long,
        val goalsFor: Long
)