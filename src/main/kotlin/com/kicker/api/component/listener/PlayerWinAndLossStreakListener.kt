package com.kicker.api.component.listener

import com.kicker.model.Game
import com.kicker.service.PlayerService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Component
class PlayerWinAndLossStreakListener(
        private val playerService: PlayerService
) {

    @EventListener
    @Transactional
    fun handleRegistrationGame(game: Game) {
        with(game) {
            playerService.updateWinAndLossStreak(winner1.id, true)
            playerService.updateWinAndLossStreak(winner2.id, true)
            playerService.updateWinAndLossStreak(loser1.id, false)
            playerService.updateWinAndLossStreak(loser2.id, false)
        }
    }

}