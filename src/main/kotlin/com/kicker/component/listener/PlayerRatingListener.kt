package com.kicker.component.listener

import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.model.PlayerStats
import com.kicker.service.PlayerService
import com.kicker.service.PlayerStatsService
import com.kicker.utils.RatingUtils
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerRatingListener(
        private val playerService: PlayerService,
        private val playerStatsService: PlayerStatsService
) {

    @EventListener
    @Transactional
    fun handleRegistrationGame(game: Game) {
        with(game) {
            val losersTotalRating: Double = getLosers().sumByDouble { it.rating }
            val winnersTotalRating: Double = getWinners().sumByDouble { it.rating }

            val skillCorrection: Double = RatingUtils.getSkillCorrection(losersTotalRating, winnersTotalRating)
            val losingPercents: Double = RatingUtils.getLosingPercents(losersGoals, skillCorrection)

            val loser1Delta = loser1.rating * losingPercents / 100.0
            val loser2Delta = loser2.rating * losingPercents / 100.0
            val winnerDelta = (loser1Delta + loser2Delta) / 2.0

            updatePlayerRating(loser1, game, false, loser1Delta.unaryMinus())
            updatePlayerRating(loser2, game, false, loser2Delta.unaryMinus())
            updatePlayerRating(winner1, game, true, winnerDelta)
            updatePlayerRating(winner2, game, true, winnerDelta)
        }
    }

    private fun updatePlayerRating(player: Player, game: Game, won: Boolean, delta: Double) {
        playerService.updateRating(player.id, (player.rating + delta))
        playerStatsService.save(PlayerStats(player, game, won, delta))
    }

}