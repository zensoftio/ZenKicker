package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.player_stats.PlayerStatsDto
import com.kicker.domain.model.player_stats.PlayerStatsPageRequest
import com.kicker.service.PlayerStatsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Yauheni Efimenko
 */

@RestController
@RequestMapping("/api/stats")
class PlayerStatsController(
        private val service: PlayerStatsService
) {

    @GetMapping("/player/{playerId}")
    fun getPlayerStats(@PathVariable playerId: Long, pageRequest: PlayerStatsPageRequest): PageResponse<PlayerStatsDto> {
        return PageResponse(service.getByPlayer(playerId, pageRequest).map { PlayerStatsDto(it) })
    }

    @GetMapping("/delta/player/{playerId}/week/{week}")
    fun getDeltaByPlayerAndWeek(@PathVariable playerId: Long, @PathVariable week: Int): Int {
        return service.getDeltaByPlayerAndWeek(playerId, week).toInt()
    }

}