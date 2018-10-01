package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.game.GameDto
import com.kicker.domain.model.game.GamePageRequest
import com.kicker.domain.model.game.GameRegistrationRequest
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
    fun getAllBelongGames(@PathVariable playerId: Long, pageRequest: GamePageRequest): PageResponse<GameDto> {
        return PageResponse(service.getAllBelongGames(playerId, pageRequest).map { GameDto(it) })
    }

    @GetMapping("/count/player/{playerId}/weekAgo/{weekAgo}")
    fun getCountGamesLastWeekByPlayer(@PathVariable playerId: Long, @PathVariable @Min(0) weekAgo: Int): Int =
            service.countGamesByPlayerAndWeek(playerId, weekAgo)

    @PostMapping("/registration")
    fun gameRegistration(@Valid @RequestBody request: GameRegistrationRequest): GameDto {
        return GameDto(service.gameRegistration(request.reportedBy!!, request))
    }

}