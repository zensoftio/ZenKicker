package io.zensoft.kicker.domain.model.playerRelations

import io.zensoft.kicker.model.PlayerRelations

/**
 * @author Yauheni Efimenko
 */

data class PlayerRelationsDashboard(
        val bestPartner: PlayerRelationsDto? = null,
        val worstPartner: PlayerRelationsDto? = null,
        val favoritePartner: PlayerRelationsDto? = null
) {

    constructor(bestPartner: PlayerRelations, worstPartner: PlayerRelations, favoritePartner: PlayerRelations) : this(
            PlayerRelationsDto(bestPartner),
            PlayerRelationsDto(worstPartner),
            PlayerRelationsDto(favoritePartner)
    )

}