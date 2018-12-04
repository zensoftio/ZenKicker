package com.kicker.api.domain.model.game

import com.kicker.api.domain.PageRequest

class GamePageRequest : PageRequest(setSortBy = setOf(ID_FIELD, DATE_FIELD)) {

    companion object {
        const val DATE_FIELD = "date"
    }

}