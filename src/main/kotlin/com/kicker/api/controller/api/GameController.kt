package com.kicker.api.controller.api

import com.kicker.api.domain.PageResponse
import com.kicker.api.domain.model.game.GameDto
import com.kicker.api.domain.model.game.GamePageRequest
import com.kicker.api.domain.model.game.GameRegistrationRequest
import com.kicker.api.model.Player
import com.kicker.api.service.GameService
import io.swagger.annotations.ApiOperation
import org.springframework.security.core.Authentication
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

    @ApiOperation(value = "Get all games", notes = """Pageable.
        * sortBy - [id, date], default:id
        * sortDirection - [ASC, DESC], default:ASC
        * offset - [0, +Infinity], default:0
        * limit - [0, +Infinity], default:10
    """)
    @GetMapping
    fun getAll(@ApiIgnore pageRequest: GamePageRequest): PageResponse<GameDto> {
        return PageResponse(service.getAll(pageRequest).map { GameDto(it) })
    }

    @ApiOperation("Get array of count of games per week during 10 weeks by player`s id")
    @GetMapping("/count/player/{playerId}/dashboard")
    fun countPerWeekDuring10WeeksByPlayer(@PathVariable playerId: Long): List<Long> =
            service.countPerWeekDuring10WeeksByPlayer(playerId)

    @ApiOperation("Registration of game")
    @PostMapping("/registration")
    fun gameRegistration(@ApiIgnore authentication: Authentication, @Valid @RequestBody request: GameRegistrationRequest): GameDto {
        val currentPlayer = authentication.principal as Player
        return GameDto(service.gameRegistration(currentPlayer.id, request))
    }

    @ApiOperation("Get array of count of games per day during last 7 days")
    @GetMapping("/count/lastWeek")
    fun getCountPerDayDuringLastWeek(): List<Long> = service.countPerDayDuringLast7Days()

}