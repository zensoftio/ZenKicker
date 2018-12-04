package com.kicker.api.domain.model.playerRelations

import com.kicker.api.domain.model.player.PlayerDto
import com.kicker.api.model.PlayerRelations
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author Yauheni Efimenko
 */

data class PlayerRelationsDto(
        val player: PlayerDto,
        val partner: PlayerDto,
        val countWins: Int,
        val countGames: Int,
        val winningPercentage: Double
) {

    constructor(playerRelations: PlayerRelations) : this(
            PlayerDto(playerRelations.player),
            PlayerDto(playerRelations.partner),
            playerRelations.countWins,
            playerRelations.countGames,
            BigDecimal(playerRelations.winningPercentage).setScale(2, RoundingMode.HALF_UP).toDouble()
    )

}