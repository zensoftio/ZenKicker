package com.kicker.service

import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.exception.service.NotFoundPlayerException
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.repository.GameRepository
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

    @Transactional
    override fun gameRegistration(currentPlayer: Player, request: GameRegistrationRequest): Game {
        val redPlayer1 = playerService.getByUsername(request.redPlayer1!!)
                ?: throw NotFoundPlayerException("The player with username ${request.redPlayer1} is not exist")
        val redPlayer2 = playerService.getByUsername(request.redPlayer2!!)
                ?: throw NotFoundPlayerException("The player with username ${request.redPlayer2} is not exist")
        val yellowPlayer1 = playerService.getByUsername(request.yellowPlayer1!!)
                ?: throw NotFoundPlayerException("The player with username ${request.yellowPlayer1} is not exist")
        val yellowPlayer2 = playerService.getByUsername(request.yellowPlayer2!!)
                ?: throw NotFoundPlayerException("The player with username ${request.yellowPlayer2} is not exist")

        val game = Game(redPlayer1, redPlayer2, yellowPlayer1, yellowPlayer2, request.redGoals!!, request.yellowGoals!!,
                LocalDateTime.now(), currentPlayer.username)

        return repository.save(game)
    }

}