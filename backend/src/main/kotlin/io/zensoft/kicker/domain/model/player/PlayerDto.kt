package io.zensoft.kicker.domain.model.player

import io.zensoft.kicker.model.Player

/**
 * @author Yauheni Efimenko
 */
data class PlayerDto(
        val id: Long,
        val email: String,
        val fullName: String,
        val iconPath: String?
) {

    constructor(player: Player) : this(
            player.id,
            player.email,
            player.fullName,
            player.iconPath
    )

}