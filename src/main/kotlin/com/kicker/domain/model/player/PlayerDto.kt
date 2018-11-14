package com.kicker.domain.model.player

import com.fasterxml.jackson.annotation.JsonInclude
import com.kicker.model.Player

/**
 * @author Yauheni Efimenko
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class PlayerDto(
        val id: Long,
        val username: String,
        val rating: Int,
        val countGames: Long,
        val rated: Long,
        val active: Boolean,
        val iconName: String?,
        val currentWinningStreak: Int,
        val currentLossesStreak: Int,
        val longestWinningStreak: Int,
        val longestLossesStreak: Int
) {

    constructor(player: Player, countGames: Long, rated: Long) : this(
            player.id,
            player.username,
            player.rating.toInt(),
            countGames,
            rated,
            player.active,
            player.iconName,
            player.currentWinningStreak,
            player.currentLossesStreak,
            player.longestWinningStreak,
            player.longestLossesStreak
    )

}