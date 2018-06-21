package com.kicker.service

import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.exception.service.NotFoundPlayerException
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.repository.GameRepository
import com.kicker.utils.RatingUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultGameService(
        private val repository: GameRepository,
        private val playerService: PlayerService
) : DefaultBaseService<Game, GameRepository>(repository), GameService {

    override fun getAllBelongGames(player: Player, pageable: Pageable): Page<Game> =
            repository.findAllBelongGames(player, pageable)

    @Transactional
    override fun gameRegistration(player: Player, request: GameRegistrationRequest): Game {
        val redPlayer1 = getPlayerByUserName(request.redPlayer1!!)
        val redPlayer2 = getPlayerByUserName(request.redPlayer2!!)
        val yellowPlayer1 = getPlayerByUserName(request.yellowPlayer1!!)
        val yellowPlayer2 = getPlayerByUserName(request.yellowPlayer2!!)

        val game = Game(redPlayer1, redPlayer2, yellowPlayer1, yellowPlayer2, request.redGoals!!, request.yellowGoals!!,
                LocalDateTime.now(), player)

        updatePlayersRating(game)

        return repository.save(game)
    }

    private fun getPlayerByUserName(username: String): Player = playerService.getByUsername(username)
            ?: throw NotFoundPlayerException("The player with username $username is not exist")

    private fun updatePlayersRating(game: Game) {
        val loserPlayer1: Player = if (game.redTeamGoals > game.yellowTeamGoals) game.yellowPlayer1 else game.redPlayer1
        val loserPlayer2: Player = if (loserPlayer1 == game.yellowPlayer1) game.yellowPlayer2 else game.redPlayer2
        val winnerPlayer1: Player = if (loserPlayer1 == game.yellowPlayer1) game.redPlayer1 else game.yellowPlayer1
        val winnerPlayer2: Player = if (loserPlayer1 == game.yellowPlayer1) game.redPlayer2 else game.yellowPlayer2

        val loserGoals: Int = if (loserPlayer1 == game.yellowPlayer1) game.yellowTeamGoals else game.redTeamGoals
        val losersTotalRating: Double = loserPlayer1.currentRating + loserPlayer2.currentRating
        val winnersTotalRating: Double = winnerPlayer1.currentRating + winnerPlayer2.currentRating

        val skillCorrection: Double = RatingUtils.getSkillCorrection(losersTotalRating, winnersTotalRating)
        val losingPercents: Double = RatingUtils.getLosingPercents(loserGoals, skillCorrection)

        val loser1Delta = loserPlayer1.currentRating * losingPercents / 100.0
        val loser2Delta = loserPlayer2.currentRating * losingPercents / 100.0
        val winnerDelta = (loser1Delta + loser2Delta) / 2.0

        playerService.updateRating(loserPlayer1, loser1Delta.unaryMinus())
        playerService.updateRating(loserPlayer2, loser2Delta.unaryMinus())
        playerService.updateRating(winnerPlayer1, winnerDelta)
        playerService.updateRating(winnerPlayer2, winnerDelta)
    }

}