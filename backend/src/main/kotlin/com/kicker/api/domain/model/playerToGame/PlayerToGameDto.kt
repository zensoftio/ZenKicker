package com.kicker.api.domain.model.playerToGame

import com.kicker.api.domain.model.game.GameDto
import com.kicker.api.model.PlayerToGame

/**
 * @author Yauheni Efimenko
 */
data class PlayerToGameDto(
        val gameDto: GameDto,
        val won: Boolean,
        val delta: Int
) {

    constructor(playerToGame: PlayerToGame) : this(
            GameDto(playerToGame.game),
            playerToGame.won,
            playerToGame.delta.toInt()
    )

}