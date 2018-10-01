package com.kicker.service

import com.kicker.domain.PageRequest
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.model.Game
import com.kicker.repository.GameRepository
import com.kicker.utils.RatingUtils
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultGameService(
        private val repository: GameRepository,
        private val playerService: PlayerService
) : DefaultBaseService<Game, GameRepository>(repository), GameService {

    override fun getAllBelongGames(playerId: Long, pageRequest: PageRequest): Page<Game> {
        val player = playerService.get(playerId)
        return repository.findAllBelongGames(player, pageRequest)
    }

    @Transactional
    override fun gameRegistration(playerId: Long, request: GameRegistrationRequest): Game {
        val reporter = playerService.get(playerId)

        val winner1 = playerService.get(request.winner1Id!!)
        val winner2 = playerService.get(request.winner2Id!!)
        val loser1 = playerService.get(request.loser1Id!!)
        val loser2 = playerService.get(request.loser2Id!!)

        val game = Game(request.losersGoals!!, winner1, winner2, loser1, loser2, reporter)

        updatePlayersRating(game)

        return repository.save(game)
    }

    private fun updatePlayersRating(game: Game) {
        with(game) {
            val losersTotalRating: Double = loser1.rating + loser2.rating
            val winnersTotalRating: Double = winner1.rating + winner2.rating

            val skillCorrection: Double = RatingUtils.getSkillCorrection(losersTotalRating, winnersTotalRating)
            val losingPercents: Double = RatingUtils.getLosingPercents(losersGoals, skillCorrection)

            val loser1Delta = loser1.rating * losingPercents / 100.0
            val loser2Delta = loser2.rating * losingPercents / 100.0
            val winnerDelta = (loser1Delta + loser2Delta) / 2.0

            playerService.updateRating(loser1.id, (loser1.rating - loser1Delta))
            playerService.updateRating(loser2.id, (loser2.rating - loser2Delta))
            playerService.updateRating(winner1.id, (winner1.rating + winnerDelta))
            playerService.updateRating(winner2.id, (winner2.rating + winnerDelta))
        }
    }

}