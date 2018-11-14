package com.kicker.domain.model.playerRelations

import com.kicker.model.PlayerRelations
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author Yauheni Efimenko
 */

data class PlayerRelationsDto(
        val playerId: Long,
        val partnerId: Long,
        val countWins: Int,
        val countGames: Int,
        val winningPercentage: Double
) {

    constructor(playerRelations: PlayerRelations) : this(
            playerRelations.player.id,
            playerRelations.partner.id,
            playerRelations.countWins,
            playerRelations.countGames,
            BigDecimal(playerRelations.winningPercentage).setScale(2, RoundingMode.HALF_UP).toDouble()
    )

}