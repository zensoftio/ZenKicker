package io.zensoft.kicker.domain.model.playerRelations

import io.zensoft.kicker.domain.PageRequest

/**
 * @author Yauheni Efimenko
 */
class PlayerRelationsPageRequest : PageRequest(mapSortBy = mapOf(ID_FIELD, WINNING_PERCENTAGE_FIELD)) {

    companion object {
        val WINNING_PERCENTAGE_FIELD = "winningPercentage" to "winning_percentage"
    }

}
