package com.kicker.api.domain.model.player

import com.kicker.api.model.Player

/**
 * @author Yauheni Efimenko
 */
data class PlayerDto(
        val id: Long,
        val username: String,
        val iconPath: String?
) {

    constructor(player: Player) : this(
            player.id,
            player.username,
            player.iconPath
    )

}