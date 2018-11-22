package com.kicker.api.domain.model.player

import com.fasterxml.jackson.annotation.JsonInclude
import com.kicker.api.model.Player

/**
 * @author Yauheni Efimenko
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class PlayerDto(
        val id: Long,
        val username: String,
        val iconName: String?
) {

    constructor(player: Player) : this(
            player.id,
            player.username,
            player.iconName
    )

}