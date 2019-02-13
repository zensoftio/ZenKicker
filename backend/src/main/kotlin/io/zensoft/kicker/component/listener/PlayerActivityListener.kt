package io.zensoft.kicker.component.listener

import io.zensoft.kicker.config.property.PlayerSettingsProperties
import io.zensoft.kicker.model.Game
import io.zensoft.kicker.model.Player
import io.zensoft.kicker.service.GameService
import io.zensoft.kicker.service.PlayerStatsService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerActivityListener(
        private val gameService: GameService,
        private val playerStatsService: PlayerStatsService,
        private val playerSettingsProperties: PlayerSettingsProperties
) {

    @EventListener
    @Transactional
    fun handleRegistrationGame(game: Game) {
        handlePlayerActivity(game.winner1)
        handlePlayerActivity(game.winner2)
        handlePlayerActivity(game.loser1)
        handlePlayerActivity(game.loser2)
    }

    private fun handlePlayerActivity(player: Player) {
        val stats = playerStatsService.getByPlayer(player.id)
        val currentCountGames = gameService.countDuring10WeeksByPlayer(player.id)
        if (!stats.active && currentCountGames >= playerSettingsProperties.countGames!!
                || stats.active && currentCountGames < playerSettingsProperties.countGames!!) {
            playerStatsService.updateActivity(player.id, !stats.active)
        }
    }

}
