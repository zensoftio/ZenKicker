package com.telegram.bot.domain

data class PlayerStatsDto(
        val rating: Int,
        val countGames: Int,
        val rated: Int,
        val countWins: Int,
        val countLosses: Int,
        val winningPercentage: Double,
        val goalsFor: Int,
        val goalsAgainst: Int,
        val longestWinningStreak: Int,
        val longestLossesStreak: Int,
        val currentWinningStreak: Int,
        val currentLossesStreak: Int,
        val active: Boolean
)