package io.zensoft.kicker.domain.model.playerStats

import io.zensoft.kicker.domain.PageRequest

class PlayerStatsPageRequest : PageRequest(mapSortBy = mapOf(ID_FIELD, RATING_FIELD, COUNT_GAMES_FIELD, RATED_FIELD,
        LONGEST_WINNING_STREAK_FIELD, LONGEST_LOSSES_STREAK_FIELD, WINNING_PERCENTAGE_FIELD)) {

    companion object {
        val RATING_FIELD = "rating" to "rating"
        val COUNT_GAMES_FIELD = "countGames" to "count_games"
        val RATED_FIELD = "rated" to "rated"
        val LONGEST_WINNING_STREAK_FIELD = "longestWinningStreak" to "longest_winning_streak"
        val LONGEST_LOSSES_STREAK_FIELD = "longestLossesStreak" to "longest_losses_streak"
        val WINNING_PERCENTAGE_FIELD = "winningPercentage" to "winning_percentage"
    }

}
