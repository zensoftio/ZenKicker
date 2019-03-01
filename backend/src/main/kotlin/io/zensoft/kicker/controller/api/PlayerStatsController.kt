package io.zensoft.kicker.controller.api

import io.swagger.annotations.ApiOperation
import io.zensoft.kicker.domain.PageResponse
import io.zensoft.kicker.domain.model.playerStats.PlayerStatsDto
import io.zensoft.kicker.domain.model.playerStats.PlayerStatsPageRequest
import io.zensoft.kicker.domain.model.playerStats.PlayersDashboard
import io.zensoft.kicker.service.PlayerStatsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

/**
 * @author Yauheni Efimenko
 */

@RestController
@RequestMapping("/api/players")
class PlayerStatsController(
        private val service: PlayerStatsService
) {

    @ApiOperation("Get stats of player by player`s id")
    @GetMapping("/{playerId}/stats")
    fun getByPlayer(@PathVariable playerId: Long): PlayerStatsDto = PlayerStatsDto(service.getByPlayer(playerId))

    @ApiOperation(value = "Get stats of all players", notes = """Pageable.
        * sortBy - [id, rating, countGames, rated, longestWinningStreak, longestLossesStreak, winningPercentage], default:id
        * sortDirection - [ASC, DESC], default:ASC
        * offset - [0, +Infinity], default:0
        * limit - [0, +Infinity], default:10
    """)
    @GetMapping("/stats")
    fun getAll(@ApiIgnore @Valid pageRequest: PlayerStatsPageRequest): PageResponse<PlayerStatsDto> =
            PageResponse(service.getAll(pageRequest).map { PlayerStatsDto(it) })

    @ApiOperation(value = "Get stats of all players", notes = """Pageable.
        * sortBy - [id, rating, countGames, rated, longestWinningStreak, longestLossesStreak, winningPercentage], default:id
        * sortDirection - [ASC, DESC], default:ASC
        * offset - [0, +Infinity], default:0
        * limit - [0, +Infinity], default:10
    """)
    @GetMapping("/active/stats")
    fun getAllActive(@ApiIgnore @Valid pageRequest: PlayerStatsPageRequest): PageResponse<PlayerStatsDto> =
            PageResponse(service.getAllActive(pageRequest).map { PlayerStatsDto(it) })

    @ApiOperation("Get dashboard of players")
    @GetMapping("/dashboard")
    fun getDashboard(): PlayersDashboard {
        val dashboard = service.getDashboard()

        if (dashboard.isEmpty()) {
            return PlayersDashboard()
        }

        return PlayersDashboard(dashboard[0], dashboard[1], dashboard[2], dashboard[3])
    }

}