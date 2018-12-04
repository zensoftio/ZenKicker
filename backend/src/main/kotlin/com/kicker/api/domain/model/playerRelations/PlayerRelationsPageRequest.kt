package com.kicker.api.domain.model.playerRelations

import com.kicker.api.domain.PageRequest

/**
 * @author Yauheni Efimenko
 */
class PlayerRelationsPageRequest : PageRequest(setSortBy = setOf(ID_FIELD, WINNING_PERCENTAGE_FIELD)) {

    companion object {
        const val WINNING_PERCENTAGE_FIELD = "winningPercentage"
    }

}