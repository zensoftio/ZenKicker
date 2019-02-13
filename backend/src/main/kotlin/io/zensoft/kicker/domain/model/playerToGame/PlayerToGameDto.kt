package io.zensoft.kicker.domain.model.playerToGame

import io.zensoft.kicker.domain.model.game.GameDto
import io.zensoft.kicker.model.PlayerToGame

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