package com.kicker.api.domain.repository

import com.kicker.model.Player
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

/**
 * @author Yauheni Efimenko
 */
data class PlayerDeltaDto(
        val id: Long,
        val username: String,
        val rating: Int,
        val active: Boolean,
        val iconName: String?,
        val delta: Double
) {

    constructor(player: Player, delta: Double) : this(
            player.id,
            player.username,
            player.rating.roundToInt(),
            player.active,
            player.iconName,
            BigDecimal(delta).setScale(2, RoundingMode.HALF_UP).toDouble()
    )

}