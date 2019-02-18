package io.zensoft.kicker.domain.model.game

import io.zensoft.kicker.domain.PageRequest

class GamePageRequest : PageRequest(setSortBy = setOf(ID_FIELD, DATE_FIELD)) {

    companion object {
        const val DATE_FIELD = "date"
    }

}