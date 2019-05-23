package io.zensoft.kicker.domain.model.game

import io.zensoft.kicker.domain.PageRequest

class GamePageRequest : PageRequest(mapSortBy = mapOf(ID_FIELD, DATE_FIELD)) {

    companion object {
        val DATE_FIELD = "date" to "date"
    }

}
