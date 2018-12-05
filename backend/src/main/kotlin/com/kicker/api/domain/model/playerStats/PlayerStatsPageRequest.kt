package com.kicker.api.domain.model.playerStats

import com.kicker.api.domain.PageRequest

class PlayerStatsPageRequest : PageRequest(setSortBy = setOf(ID_FIELD, RATING_FIELD)) {

    companion object {
        const val RATING_FIELD = "rating"
    }

}