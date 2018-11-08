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

    @GetMapping("/count/player/{playerId}/dashboard")
    fun countPerWeekDuring10WeeksByPlayer(@PathVariable playerId: Long): List<Long> =
            service.countPerWeekDuring10WeeksByPlayer(playerId)

    @PostMapping("/registration")
    fun gameRegistration(@CurrentPlayer currentPlayer: Player, @Valid @RequestBody request: GameRegistrationRequest): GameDto {
        return GameDto(service.gameRegistration(currentPlayer.id, request))
    }

    @GetMapping("/count/lastWeek")
    fun getCountByLastWeek(): List<Long> = service.countPerDayDuringLast7Days()

}