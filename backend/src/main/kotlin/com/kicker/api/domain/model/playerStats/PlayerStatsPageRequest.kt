package com.kicker.api.domain.model.playerStats

import com.kicker.api.domain.PageRequest

class PlayerStatsPageRequest : PageRequest(setSortBy = setOf(ID_FIELD, RATING_FIELD, COUNT_GAMES_FIELD, RATED_FIELD)) {

    companion object {
        const val RATING_FIELD = "rating"
        const val COUNT_GAMES_FIELD = "countGames"
        const val RATED_FIELD = "rated"
    }

}