package io.zensoft.kicker.controller.api

import io.swagger.annotations.ApiOperation
import io.zensoft.kicker.annotation.CurrentPlayer
import io.zensoft.kicker.domain.PageResponse
import io.zensoft.kicker.domain.model.game.GameDto
import io.zensoft.kicker.domain.model.game.GamePageRequest
import io.zensoft.kicker.domain.model.game.GameRegistrationRequest
import io.zensoft.kicker.model.Player
import io.zensoft.kicker.service.GameService
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/games")
class GameController(
        private val service: GameService
) {

    @ApiOperation(value = "Get all games", notes = """Pageable.
        * sortBy - [id, date], default:id
        * sortDirection - [ASC, DESC], default:ASC
        * offset - [0, +Infinity], default:0
        * limit - [0, +Infinity], default:10
    """)
    @GetMapping
    fun getAll(@ApiIgnore @Valid pageRequest: GamePageRequest): PageResponse<GameDto> {
        return PageResponse(service.getAll(pageRequest).map { GameDto(it) })
    }

    @ApiOperation("Get array of count of games per week during 10 weeks by player`s id")
    @GetMapping("/count/player/{playerId}/dashboard")
    fun countPerWeekDuring10WeeksByPlayer(@PathVariable playerId: Long): List<Long> =
            service.countPerWeekDuring10WeeksByPlayer(playerId)

    @ApiOperation("Registration of game")
    @PostMapping("/registration")
    fun gameRegistration(
            @ApiIgnore @CurrentPlayer currentPlayer: Player,
            @Valid @RequestBody request: GameRegistrationRequest): GameDto {
        return GameDto(service.gameRegistration(currentPlayer.id, request))
    }

    @ApiOperation("Get array of count of games per day during last 7 days")
    @GetMapping("/count/lastWeek")
    fun getCountPerDayDuringLastWeek(): List<Long> = service.countPerDayDuringLast7Days()

}