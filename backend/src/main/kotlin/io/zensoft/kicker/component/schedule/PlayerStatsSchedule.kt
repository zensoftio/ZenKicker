package io.zensoft.kicker.component.schedule

import io.zensoft.kicker.service.PlayerService
import io.zensoft.kicker.service.PlayerStatsService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerStatsSchedule(
        private val playerService: PlayerService,
        private val playerStatsService: PlayerStatsService
) {

    @Scheduled(cron = "0 0 0 * * MON")
    @Transactional
    fun schedulePlayersRating() {
        playerService.getAll().forEach {
            playerStatsService.updateRatingAndRated(it)
        }
    }

}