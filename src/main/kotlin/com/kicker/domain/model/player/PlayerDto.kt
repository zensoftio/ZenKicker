package com.kicker.domain.model.player

import com.kicker.model.Player

/**
 * @author Yauheni Efimenko
 */
data class PlayerDto(
        val id: Long,
        val username: String,
        val rating: Int,
        val countGames: Long,
        val rated: Long,
        val active: Boolean
) {

    constructor(player: Player, countGames: Long, rated: Long) : this(
            player.id,
            player.username,
            player.rating.toInt(),
            countGames,
            rated,
            player.active
    )

}