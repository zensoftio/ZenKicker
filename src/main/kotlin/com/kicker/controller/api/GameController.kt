package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.PageResponse
import com.kicker.domain.model.game.GameDto
import com.kicker.domain.model.game.GamePageRequest
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.model.Player
import com.kicker.service.GameService
import io.swagger.annotations.ApiOperation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/games")
@Validated
class GameController(
        private val service: GameService
) {

    @ApiOperation("Get game by id")
    @GetMapping("/{gameId}")
    fun get(@PathVariable gameId: Long): GameDto = GameDto(service.get(gameId))

    @ApiOperation(value = "Get all games", notes = """Pageable.
        * sortBy - [id,date], default:date
        * sortDirection - [ASC,DESC], default:DESC
    """)
    @GetMapping
    fun getAll(@ApiIgnore pageRequest: GamePageRequest): PageResponse<GameDto> {
        return PageResponse(service.getAll(pageRequest).map { GameDto(it) })
    }

    @ApiOperation(value = "Get all games of player by player`s id", notes = """Pageable.
        * sortBy - [id,date], default:date
        * sortDirection - [ASC,DESC], default:DESC
    """)
    @GetMapping("/player/{playerId}")
    fun getAllByPlayer(@PathVariable playerId: Long, @ApiIgnore pageRequest: GamePageRequest): PageResponse<GameDto> {
        return PageResponse(service.getAllByPlayer(playerId, pageRequest).map { GameDto(it) })
    }

    @ApiOperation("Get array of count of games per week during 10 weeks by player`s id")
    @GetMapping("/count/player/{playerId}/dashboard")
    fun countPerWeekDuring10WeeksByPlayer(@PathVariable playerId: Long): List<Long> =
            service.countPerWeekDuring10WeeksByPlayer(playerId)

    @ApiOperation("Registration of game")
    @PostMapping("/registration")
    fun gameRegistration(@CurrentPlayer currentPlayer: Player, @Valid @RequestBody request: GameRegistrationRequest): GameDto {
        return GameDto(service.gameRegistration(currentPlayer.id, request))
    }

    @ApiOperation("Get array of count of games per day during last 7 days by player`s id")
    @GetMapping("/count/lastWeek")
    fun getCountPerDayDuringLastWeek(): List<Long> = service.countPerDayDuringLast7Days()

}