package io.zensoft.kicker.domain.model.playerRelations

import io.zensoft.kicker.domain.PageRequest

/**
 * @author Yauheni Efimenko
 */
class PlayerRelationsPageRequest : PageRequest(setSortBy = setOf(ID_FIELD, WINNING_PERCENTAGE_FIELD)) {

    companion object {
        const val WINNING_PERCENTAGE_FIELD = "winningPercentage"
    }

}