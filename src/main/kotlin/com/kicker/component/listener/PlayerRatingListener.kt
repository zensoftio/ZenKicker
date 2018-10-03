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
            val loser1Rating = playerStatsService.getActualRatingByPlayer(loser1.id)
            val loser2Rating = playerStatsService.getActualRatingByPlayer(loser2.id)
            val winner1Rating = playerStatsService.getActualRatingByPlayer(winner1.id)
            val winner2Rating = playerStatsService.getActualRatingByPlayer(winner2.id)

            val losersTotalRating: Double = loser1Rating.plus(loser2Rating)
            val winnersTotalRating: Double = winner1Rating.plus(winner2Rating)

            val skillCorrection: Double = RatingUtils.getSkillCorrection(losersTotalRating, winnersTotalRating)
            val losingPercents: Double = RatingUtils.getLosingPercents(losersGoals, skillCorrection)

            val loser1Delta = loser1Rating * losingPercents / 100.0
            val loser2Delta = loser2Rating * losingPercents / 100.0
            val winnerDelta = (loser1Delta + loser2Delta) / 2.0

            updatePlayerRating(loser1, game, false, loser1Rating, loser1Delta.unaryMinus())
            updatePlayerRating(loser2, game, false, loser2Rating, loser2Delta.unaryMinus())
            updatePlayerRating(winner1, game, true, winner1Rating, winnerDelta)
            updatePlayerRating(winner2, game, true, winner2Rating, winnerDelta)
        }
    }

    private fun updatePlayerRating(player: Player, game: Game, won: Boolean, rating: Double, delta: Double) {
        playerService.updateRating(player.id, (rating + delta))
        playerStatsService.save(PlayerStats(player, game, won, delta))
    }

}