package io.zensoft.kicker.domain.model.playerStats

import io.zensoft.kicker.domain.model.player.PlayerDto
import io.zensoft.kicker.model.Player

data class PlayersDashboard(
        val firstPlace: PlayerDto? = null,
        val secondPlace: PlayerDto? = null,
        val thirdPlace: PlayerDto? = null,
        val loser: PlayerDto? = null
) {

    constructor(firstPlace: Player, secondPlace: Player, thirdPlace: Player, loser: Player) : this(
            PlayerDto(firstPlace),
            PlayerDto(secondPlace),
            PlayerDto(thirdPlace),
            PlayerDto(loser)
    )

}