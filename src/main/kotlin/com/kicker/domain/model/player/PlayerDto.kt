package com.kicker.domain.model.player

import com.kicker.model.Player

/**
 * @author Yauheni Efimenko
 */
data class PlayerDto(
        val id: Long,
        val username: String,
        val active: Boolean
) {
    constructor(player: Player) : this(
            player.id,
            player.username,
            player.active
    )
}