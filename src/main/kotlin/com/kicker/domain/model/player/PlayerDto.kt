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
        val currentWinStreak: Int,
        val currentLossStreak: Int,
        val longestWinStreak: Int,
        val longestLossStreak: Int
) {

    constructor(player: Player, countGames: Long, rated: Long) : this(
            player.id,
            player.username,
            player.rating.toInt(),
            countGames,
            rated,
            player.active,
            player.iconName,
            player.getCurrentWinStreak(),
            player.getCurrentLossStreak(),
            player.getLongestWinStreak(),
            player.getLongestLossStreak()
    )

}