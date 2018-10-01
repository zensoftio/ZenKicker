package com.kicker.service

import com.kicker.domain.PageRequest
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.model.PlayerStats
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
        private val playerService: PlayerService,
        private val playerStatsService: PlayerStatsService
) : DefaultBaseService<Game, GameRepository>(repository), GameService {

    override fun getAllBelongGames(playerId: Long, pageRequest: PageRequest): Page<Game> {
        val player = playerService.get(playerId)
        return repository.findAllBelongGames(player, pageRequest)
    }

    override fun countGamesLastWeekByPlayer(playerId: Long): Int {
        playerService.get(playerId) // validate to exist or exception

        return repository.countGamesLastWeekByPlayer(playerId)
    }

    @Transactional
    override fun gameRegistration(playerId: Long, request: GameRegistrationRequest): Game {
        val reporter = playerService.get(playerId)

        val winner1 = playerService.get(request.winner1Id!!)
        val winner2 = playerService.get(request.winner2Id!!)
        val loser1 = playerService.get(request.loser1Id!!)
        val loser2 = playerService.get(request.loser2Id!!)

        val game = Game(request.losersGoals!!, winner1, winner2, loser1, loser2, reporter)
        val persistGame = repository.save(game)

        updatePlayersRating(persistGame)

        return persistGame
    }

    private fun updatePlayersRating(game: Game) {
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