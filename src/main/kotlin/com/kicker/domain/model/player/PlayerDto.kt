package com.kicker.domain.model.player

import com.kicker.domain.model.award.AwardDto
import com.kicker.model.Player

/**
 * @author Yauheni Efimenko
 */
data class PlayerDto(
        val id: Long,
        val username: String,
        val firstName: String,
        val lastName: String,
        val active: Boolean,
        val awards: List<AwardDto>
) {
    constructor(player: Player) : this(
            player.id,
            player.username,
            player.firstName,
            player.lastName,
            player.active,
            player.awards.map { AwardDto(it) }
    )
}