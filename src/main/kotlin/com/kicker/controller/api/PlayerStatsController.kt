package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.player_stats.PlayerGamesStatsDto
import com.kicker.domain.model.player_stats.PlayerStatsDto
import com.kicker.domain.model.player_stats.PlayerStatsPageRequest
import com.kicker.model.Player
import com.kicker.service.PlayerStatsService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Yauheni Efimenko
 */

@RestController
@RequestMapping("/api/stats")
@Validated
class PlayerStatsController(
        private val service: PlayerStatsService
) {

    @GetMapping("/player/{playerId}")
    fun getPlayerStats(@PathVariable playerId: Long, pageRequest: PlayerStatsPageRequest): PlayerStatsDto {
        return service.getStatsByPlayer(playerId)
    }

    @GetMapping("/games/player/{playerId}")
    fun getPlayerGamesStats(@PathVariable playerId: Long, pageRequest: PlayerStatsPageRequest): PageResponse<PlayerGamesStatsDto> {
        return PageResponse(service.getGamesStatsByPlayer(playerId, pageRequest).map { PlayerGamesStatsDto(it) })
    }

    @GetMapping("/delta/player/{playerId}/dashboard")
    fun getDeltaPerWeekDuring10WeeksByPlayer(@PathVariable playerId: Long): List<Int> =
            service.getDeltaPerWeekDuring10WeeksByPlayer(playerId).map { Player.PLAYER_RATING.toInt() + it.toInt() }

}