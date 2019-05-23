package io.zensoft.kicker.component.listener

import io.zensoft.kicker.config.property.GamesSettingsProperties
import io.zensoft.kicker.config.property.PlayerSettingsProperties
import io.zensoft.kicker.model.Game
import io.zensoft.kicker.model.PlayerStats
import io.zensoft.kicker.model.PlayerToGame
import io.zensoft.kicker.service.PlayerStatsService
import io.zensoft.kicker.service.PlayerToGameService
import io.zensoft.kicker.utils.RatingUtils
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerStatsListener(
        private val playerToGameService: PlayerToGameService,
        private val playerStatsService: PlayerStatsService,
        private val gamesSettingsProperties: GamesSettingsProperties,
        private val playerSettingsProperties: PlayerSettingsProperties
) {

    @Transactional
    @EventListener
    fun handleRegistrationGame(game: Game) {
        with(game) {
            val loser1Stats = playerStatsService.getByPlayer(loser1.id)
            val loser2Stats = playerStatsService.getByPlayer(loser2.id)
            val winner1Stats = playerStatsService.getByPlayer(winner1.id)
            val winner2Stats = playerStatsService.getByPlayer(winner2.id)


            val losersTotalRating: Double = loser1Stats.rating.plus(loser2Stats.rating)
            val winnersTotalRating: Double = winner1Stats.rating.plus(winner2Stats.rating)

            val skillCorrection: Double = RatingUtils.getSkillCorrection(losersTotalRating, winnersTotalRating)
            val losingPercents: Double = RatingUtils.getLosingPercents(losersGoals, skillCorrection)

            val loser1Delta = loser1Stats.rating * losingPercents / 100.0
            val loser2Delta = loser2Stats.rating * losingPercents / 100.0
            val winnerDelta = (loser1Delta + loser2Delta) / 2.0

            updatePlayerStats(loser1Stats, game, false, loser1Delta.unaryMinus())
            updatePlayerStats(loser2Stats, game, false, loser2Delta.unaryMinus())
            updatePlayerStats(winner1Stats, game, true, winnerDelta)
            updatePlayerStats(winner2Stats, game, true, winnerDelta)
        }
    }

    private fun updatePlayerStats(stats: PlayerStats, game: Game, won: Boolean, delta: Double) {
        if (won) {
            playerStatsService.add(PlayerStats(stats, game, won, game.losersGoals,
                    gamesSettingsProperties.defaultMaxScore!!, delta, playerSettingsProperties.countGames!!))
        } else {
            playerStatsService.add(PlayerStats(stats, game, won, gamesSettingsProperties.defaultMaxScore!!,
                    game.losersGoals, delta, playerSettingsProperties.countGames!!))
        }

        playerToGameService.add(PlayerToGame(stats.player, game, won, delta))
    }

}
