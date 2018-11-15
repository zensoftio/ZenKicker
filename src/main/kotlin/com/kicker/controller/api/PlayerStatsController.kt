package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.playerStats.PlayerGamesStatsDto
import com.kicker.domain.model.playerStats.PlayerStatsDto
import com.kicker.domain.model.playerStats.PlayerStatsPageRequest
import com.kicker.domain.repository.PlayerDeltaDto
import com.kicker.model.Player
import com.kicker.service.PlayerStatsService
import io.swagger.annotations.ApiOperation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import kotlin.math.roundToInt

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
    fun getPlayerStats(@PathVariable playerId: Long): PlayerStatsDto {
        return service.getStatsByPlayer(playerId)
    }

    @ApiOperation(value = "getPlayerGamesStats - Remember! You can use player stats page request")
    @GetMapping("/games/player/{playerId}")
    fun getPlayerGamesStats(@PathVariable playerId: Long, @ApiIgnore pageRequest: PlayerStatsPageRequest): PageResponse<PlayerGamesStatsDto> {
        return PageResponse(service.getGamesStatsByPlayer(playerId, pageRequest).map { PlayerGamesStatsDto(it) })
    }

    @GetMapping("/delta/player/{playerId}/dashboard")
    fun getDeltaPerWeekDuring10WeeksByPlayer(@PathVariable playerId: Long): List<Int> =
            service.getDeltaPerWeekDuring10WeeksByPlayer(playerId).map { (Player.PLAYER_RATING + it).roundToInt() }

    @GetMapping("/players/delta")
    fun getDeltaPlayersForLastWeek(): List<PlayerDeltaDto> = service.getDeltaPlayersForLastWeek()

}