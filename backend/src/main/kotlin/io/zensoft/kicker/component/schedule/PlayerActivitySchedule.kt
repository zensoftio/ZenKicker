package io.zensoft.kicker.component.schedule

import io.zensoft.kicker.config.property.PlayerSettingsProperties
import io.zensoft.kicker.service.GameService
import io.zensoft.kicker.service.PlayerService
import io.zensoft.kicker.service.PlayerStatsService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerActivitySchedule(
        private val gameService: GameService,
        private val playerService: PlayerService,
        private val playerStatsService: PlayerStatsService,
        private val playerSettingsProperties: PlayerSettingsProperties
) {

    @Scheduled(cron = "0 10 0 * * MON")
    @Transactional
    fun schedulePlayerActivity() {
        playerService.getAll().forEach {
            val stats = playerStatsService.getByPlayer(it.id)
            if (stats.active && gameService.countDuring10WeeksByPlayer(it.id) < playerSettingsProperties.countGames!!) {
                playerStatsService.updateActivity(it.id, !stats.active)
            }
        }
    }

}