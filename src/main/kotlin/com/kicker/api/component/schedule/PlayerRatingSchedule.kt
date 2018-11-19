package com.kicker.api.component.schedule

import com.kicker.service.PlayerService
import com.kicker.service.PlayerStatsService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerRatingSchedule(
        private val playerService: PlayerService,
        private val playerStatsService: PlayerStatsService
) {

    @Scheduled(cron = "0 0 0 * * MON")
    @Transactional
    fun schedulePlayersRating() {
        playerService.getAll().forEach {
            playerService.updateRating(it.id, playerStatsService.getActualRatingByPlayer(it.id))
        }
    }

}