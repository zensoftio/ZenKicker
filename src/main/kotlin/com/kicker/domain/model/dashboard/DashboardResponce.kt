package com.kicker.domain.model.dashboard

import com.kicker.model.Player

/**
 * @author Yauheni Efimenko
 */
data class DashboardResponce(
        val ratings: List<DashboardRatingDto>,
        val initialRating: Int
) {
    constructor(ratings: List<DashboardRatingDto>) : this(ratings, Player.INITIAL_RATING.toInt())
}