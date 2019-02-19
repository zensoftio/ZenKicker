package io.zensoft.kicker.domain.model.player

import io.zensoft.kicker.model.Player

/**
 * @author Yauheni Efimenko
 */
data class PlayerDto(
        val id: Long,
        val login: String,
        val fullName: String,
        val iconPath: String?
) {

    constructor(player: Player) : this(
            player.id,
            player.login,
            player.fullName,
            player.iconPath
    )

}