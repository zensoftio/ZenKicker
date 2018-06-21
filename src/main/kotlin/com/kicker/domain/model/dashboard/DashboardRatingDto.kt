package com.kicker.domain.model.dashboard

import com.kicker.model.DashboardRating

/**
 * @author Yauheni Efimenko
 */
data class DashboardRatingDto(
        val delta: Int,
        val weeksAgo: Int
) {
    constructor(dashboardRating: DashboardRating) : this(dashboardRating.delta.toInt(), dashboardRating.weeksAgo)
}