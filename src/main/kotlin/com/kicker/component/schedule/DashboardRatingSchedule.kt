package com.kicker.component.schedule

import com.kicker.service.DashboardRatingService
import com.kicker.service.PlayerService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Component
class DashboardRatingSchedule(
        private val playerService: PlayerService,
        private val dashboardRatingService: DashboardRatingService
) {

    @Scheduled(cron = "0 0 0 * * MON")
    @Transactional
    fun updateRating() {
        val players = playerService.getAll()
        players.forEach { dashboardRatingService.recalculate(it) }
    }

}