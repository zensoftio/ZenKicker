package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.PageResponse
import com.kicker.domain.model.game.GameDto
import com.kicker.domain.model.game.GamePageRequest
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.model.Player
import com.kicker.service.GameService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/games")
@Validated
class GameController(
        private val service: GameService
) {

    @GetMapping("/{gameId}")
    fun get(@PathVariable gameId: Long): GameDto = GameDto(service.get(gameId))

    @GetMapping
    fun getAll(pageRequest: GamePageRequest): PageResponse<GameDto> {
        return PageResponse(service.getAll(pageRequest).map { GameDto(it) })
    }

    @GetMapping("/player/{playerId}")
    fun getAllByPlayer(@PathVariable playerId: Long, pageRequest: GamePageRequest): PageResponse<GameDto> {
        return PageResponse(service.getAllByPlayer(playerId, pageRequest).map { GameDto(it) })
    }

    @Deprecated("Return count of games for 10 weeks")
    @GetMapping("/count/player/{playerId}/weeksAgo/{weeksAgo}")
    fun getCountByPlayerAndWeeksAgo(@PathVariable playerId: Long, @PathVariable @Min(0) weeksAgo: Long): Long =
            service.countByPlayerAndWeeksAgo(playerId, weeksAgo)

    @PostMapping("/registration")
    fun gameRegistration(@CurrentPlayer currentPlayer: Player, @Valid @RequestBody request: GameRegistrationRequest): GameDto {
        return GameDto(service.gameRegistration(currentPlayer.id, request))
    }

}