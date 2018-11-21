package com.kicker.api.component.schedule

import com.kicker.api.service.GameService
import com.kicker.api.service.PlayerService
import com.kicker.api.service.PlayerStatsService
import com.kicker.api.service.PlayerToGameService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerRatingSchedule(
        private val playerService: PlayerService,
        private val playerStatsService: PlayerStatsService,
        private val playerToGameService: PlayerToGameService,
        private val gameService: GameService
) {

    @Scheduled(cron = "0 0 0 * * MON")
    @CacheEvict("statsActivePlayers", allEntries = true)
    @Transactional
    fun schedulePlayersRating() {
        playerService.getAll().forEach {
            val stats = playerStatsService.getByPlayer(it.id)

            stats.rating = playerToGameService.getActualRatingByPlayer(it.id)
            stats.rated = gameService.countDuring10WeeksByPlayer(it.id).toInt()
            playerStatsService.save(stats)
        }
    }

}