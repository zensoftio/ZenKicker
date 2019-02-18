package io.zensoft.kicker.domain.model.playerStats

import io.zensoft.kicker.domain.model.player.PlayerDto
import io.zensoft.kicker.model.PlayerStats
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

/**
 * @author Yauheni Efimenko
 */

data class PlayerStatsDto(
        val player: PlayerDto,
        val rating: Int,
        val currentWinningStreak: Int,
        val currentLossesStreak: Int,
        val longestWinningStreak: Int,
        val longestLossesStreak: Int,
        val countLosses: Int,
        val countWins: Int,
        val countGames: Int,
        val rated: Int,
        val winningPercentage: Double,
        val goalsAgainst: Int,
        val goalsFor: Int,
        val active: Boolean
) {

    constructor(playerStats: PlayerStats) : this(
            PlayerDto(playerStats.player),
            playerStats.rating.roundToInt(),
            playerStats.currentWinningStreak,
            playerStats.currentLossesStreak,
            playerStats.longestWinningStreak,
            playerStats.longestLossesStreak,
            playerStats.countGames.minus(playerStats.countWins),
            playerStats.countWins,
            playerStats.countGames,
            playerStats.rated,
            BigDecimal(playerStats.winningPercentage).setScale(2, RoundingMode.HALF_UP).toDouble(),
            playerStats.goalsAgainst,
            playerStats.goalsFor,
            playerStats.active
    )

}