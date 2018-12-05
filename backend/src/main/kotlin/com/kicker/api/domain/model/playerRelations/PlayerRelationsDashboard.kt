package com.kicker.api.domain.model.playerRelations

import com.kicker.api.model.PlayerRelations

/**
 * @author Yauheni Efimenko
 */

data class PlayerRelationsDashboard(
        val bestPartner: PlayerRelationsDto? = null,
        val worstPartner: PlayerRelationsDto? = null,
        val favoritePartner: PlayerRelationsDto? = null
) {

    constructor(bestPartner: PlayerRelations?, worstPartner: PlayerRelations?, favoritePartner: PlayerRelations?) : this(
            bestPartner?.let { PlayerRelationsDto(it) },
            worstPartner?.let { PlayerRelationsDto(it) },
            favoritePartner?.let { PlayerRelationsDto(it) }
    )

}