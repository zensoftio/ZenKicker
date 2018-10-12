package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.player_stats.PlayerStatsDto
import com.kicker.domain.model.player_stats.PlayerStatsPageRequest
import com.kicker.service.PlayerStatsService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Min

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
    fun getPlayerStats(@PathVariable playerId: Long, pageRequest: PlayerStatsPageRequest): PageResponse<PlayerStatsDto> {
        return PageResponse(service.getByPlayer(playerId, pageRequest).map { PlayerStatsDto(it) })
    }

    @GetMapping("/delta/player/{playerId}/weeksAgo/{weeksAgo}")
    fun getDeltaByPlayerAndWeeksAgo(@PathVariable playerId: Long, @PathVariable @Min(0) weeksAgo: Long): Int =
            service.getDeltaByPlayerAndWeeksAgo(playerId, weeksAgo).toInt()

    @GetMapping("/player/{playerId}/losses")
    fun countLossesByPlayer(@PathVariable playerId: Long): Long {
        return service.countLossesByPlayer(playerId)
    }

    @GetMapping("/player/{playerId}/victories")
    fun getVictoriesByPlayer(@PathVariable playerId: Long): Long {
        return service.countVictoriesByPlayer(playerId)
    }

}