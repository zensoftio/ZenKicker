package com.kicker.component.schedule

import com.kicker.service.GameService
import com.kicker.service.PlayerService
import org.springframework.beans.factory.annotation.Value
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
        @Value("\${players.games.count}") val countGames: Long
) {

    @Scheduled(cron = "0 10 0 * * MON")
    @Transactional
    fun schedulePlayerActivity() {
        playerService.getAll().forEach {
            if (it.active && gameService.countFor10WeeksByPlayer(it.id) < countGames) {
                playerService.updateActivity(it.id, !it.active)
            }
        }
    }

}