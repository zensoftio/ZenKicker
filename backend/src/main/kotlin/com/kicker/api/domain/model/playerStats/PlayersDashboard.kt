package com.kicker.api.domain.model.playerStats

import com.kicker.api.domain.model.player.PlayerDto
import com.kicker.api.model.Player

data class PlayersDashboard(
        val firstPlace: PlayerDto? = null,
        val secondPlace: PlayerDto? = null,
        val thirdPlace: PlayerDto? = null,
        val loser: PlayerDto? = null
) {

    constructor(firstPlace: Player?, secondPlace: Player?, thirdPlace: Player?, loser: Player?) : this(
            firstPlace?.let { PlayerDto(it) },
            secondPlace?.let { PlayerDto(it) },
            thirdPlace?.let { PlayerDto(it) },
            loser?.let { PlayerDto(it) }
    )

}