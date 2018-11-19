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

    @ApiOperation("Get stats of player by player`s id")
    @GetMapping("/player/{playerId}")
    fun getPlayerStats(@PathVariable playerId: Long): PlayerStatsDto {
        return service.getStatsByPlayer(playerId)
    }

    @ApiOperation(value = "Get all games stats of player by player`s id", notes = """Pageable.
        * sortDirection - [ASC,DESC], default:DESC
    """)
    @GetMapping("/games/player/{playerId}")
    fun getPlayerGamesStats(@PathVariable playerId: Long, @ApiIgnore pageRequest: PlayerStatsPageRequest): PageResponse<PlayerGamesStatsDto> {
        return PageResponse(service.getGamesStatsByPlayer(playerId, pageRequest).map { PlayerGamesStatsDto(it) })
    }

    @ApiOperation("Get delta of rating per week during 10 weeks by player`s id")
    @GetMapping("/delta/player/{playerId}/dashboard")
    fun getDeltaPerWeekDuring10WeeksByPlayer(@PathVariable playerId: Long): List<Int> =
            service.getDeltaPerWeekDuring10WeeksByPlayer(playerId).map { (Player.PLAYER_RATING + it).roundToInt() }

    @ApiOperation("Get array of delta of players during last week")
    @GetMapping("/players/delta")
    fun getDeltaPlayersDuringLastWeek(): List<PlayerDeltaDto> = service.getDeltaPlayersDuringLastWeek()

}