package com.kicker.domain.model

import com.kicker.model.Player

/**
 * @author Yauheni Efimenko
 */
data class PlayerDto(
        val id: Long,
        val username: String,
        val firstName: String,
        val lastName: String
) {
    constructor(player: Player) : this(player.id, player.username, player.firstName, player.lastName)
}