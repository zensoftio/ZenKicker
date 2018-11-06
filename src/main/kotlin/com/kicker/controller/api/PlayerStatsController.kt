package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.player_stats.PlayerGamesStatsDto
import com.kicker.domain.model.player_stats.PlayerStatsDto
import com.kicker.domain.model.player_stats.PlayerStatsPageRequest
import com.kicker.service.PlayerStatsService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Max
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
    fun getPlayerStats(@PathVariable playerId: Long, pageRequest: PlayerStatsPageRequest): PlayerStatsDto {
        return service.getStatsByPlayer(playerId)
    }

    @GetMapping("/games/player/{playerId}")
    fun getPlayerGamesStats(@PathVariable playerId: Long, pageRequest: PlayerStatsPageRequest): PageResponse<PlayerGamesStatsDto> {
        return PageResponse(service.getGamesStatsByPlayer(playerId, pageRequest).map { PlayerGamesStatsDto(it) })
    }

    /**
     * 0 is the current week
     * 9 is the tenth week
     */
    @GetMapping("/delta/player/{playerId}/dashboard/countWeeks/{countWeeks}")
    fun getDeltaByPlayerAndWeeksAgo(@PathVariable playerId: Long,
                                    @PathVariable @Min(0) @Max(9) countWeeks: Long): List<Int> =
            service.getDeltaByPlayerForWeeksAgo(playerId, countWeeks).map { it.toInt() }

}