package com.kicker.controller.api

import com.kicker.domain.model.game.GameDto
import com.kicker.domain.model.game.GamePageRequest
import com.kicker.domain.model.game.GamePageResponse
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.domain.model.player.PlayerDto
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.service.GameService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/games")
class GameController(
        private val service: GameService
) {

    @GetMapping("/{gameId}")
    fun get(@PathVariable gameId: Long): GameDto = GameDto(service.get(gameId))

    @GetMapping
    fun getAll(@Valid pageRequest: GamePageRequest): GamePageResponse {
        val games = service.getAll(pageRequest)
        return GamePageResponse(games.map { GameDto(it) }, getPlayers(games).map { PlayerDto(it) })
    }

    @GetMapping("/belong/{playerId}")
    fun getAllBelongGames(@PathVariable playerId: Long, @Valid pageRequest: GamePageRequest): GamePageResponse {
        val games = service.getAllBelongGames(playerId, pageRequest)
        return GamePageResponse(games.map { GameDto(it) }, getPlayers(games).map { PlayerDto(it) })
    }

    @PostMapping("/{playerId}")
    fun gameRegistration(@PathVariable playerId: Long, @Valid @RequestBody request: GameRegistrationRequest): GameDto {
        return GameDto(service.gameRegistration(playerId, request))
    }

    private fun getPlayers(games: Page<Game>): Set<Player> {
        val players = mutableSetOf<Player>()
        games.forEach {
            players.addAll(setOf(it.redPlayer1, it.redPlayer2, it.yellowPlayer1, it.yellowPlayer2, it.reportedBy))
        }
        return players
    }

}