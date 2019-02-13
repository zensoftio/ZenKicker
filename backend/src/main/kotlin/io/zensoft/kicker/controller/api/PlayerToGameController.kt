package io.zensoft.kicker.controller.api

import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.domain.PageResponse
import io.zensoft.kicker.domain.model.playerToGame.PlayerToGameDto
import io.zensoft.kicker.service.PlayerToGameService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid
import kotlin.math.roundToInt

/**
 * @author Yauheni Efimenko
 */

@RestController
@RequestMapping("/api/players")
class PlayerToGameController(
        private val service: PlayerToGameService
) {

    @ApiOperation(value = "Get info about all the games of player by player`s id", notes = """Pageable.
        * sortBy - [id], default:id
        * sortDirection - [ASC, DESC], default:ASC
        * offset - [0, +Infinity], default:0
        * limit - [0, +Infinity], default:10
    """)
    @GetMapping("/{playerId}/games")
    fun getPlayerGames(@PathVariable playerId: Long, @ApiIgnore @Valid pageRequest: PageRequest): PageResponse<PlayerToGameDto> =
            PageResponse(service.getPlayerGames(playerId, pageRequest).map { PlayerToGameDto(it) })

    @ApiOperation("Get delta of rating per week during 10 weeks by player`s id")
    @GetMapping("/{playerId}/delta/dashboard")
    fun getDeltaPerWeekDuring10WeeksByPlayer(@PathVariable playerId: Long): List<Int> =
            service.getDeltaPerWeekDuring10WeeksByPlayer(playerId).map { it.roundToInt() }

}