package io.zensoft.kicker.component.schedule

import io.zensoft.kicker.service.PlayerService
import io.zensoft.kicker.service.PlayerStatsService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerStatsSchedule(
        private val playerService: PlayerService,
        private val playerStatsService: PlayerStatsService
) {

    @CacheEvict("playersDashboard", allEntries = true)
    @Scheduled(cron = "0 0 0 * * MON")
    fun schedulePlayersRating() {
        playerService.getAll().forEach {
            playerStatsService.updateStatsAfterWeek(it)
        }
    }

}