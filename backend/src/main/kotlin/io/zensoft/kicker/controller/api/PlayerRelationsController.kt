package io.zensoft.kicker.controller.api

import io.swagger.annotations.ApiOperation
import io.zensoft.kicker.domain.PageResponse
import io.zensoft.kicker.domain.model.playerRelations.PlayerRelationsDashboard
import io.zensoft.kicker.domain.model.playerRelations.PlayerRelationsDto
import io.zensoft.kicker.domain.model.playerRelations.PlayerRelationsPageRequest
import io.zensoft.kicker.service.PlayerRelationsService
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
class PlayerRelationsController(
        private val service: PlayerRelationsService
) {

    @ApiOperation(value = "Get all relations by player`s id", notes = """Pageable.
        * sortBy - [id, winningPercentage], default:id
        * sortDirection - [ASC, DESC], default:ASC
        * offset - [0, +Infinity], default:0
        * limit - [0, +Infinity], default:10
    """)
    @GetMapping("/{playerId}/relations")
    fun getAllByPlayer(@PathVariable playerId: Long, @ApiIgnore @Valid pageRequest: PlayerRelationsPageRequest): PageResponse<PlayerRelationsDto> {
        return PageResponse(service.getAllByPlayer(playerId, pageRequest).map { PlayerRelationsDto(it) })
    }

    @ApiOperation("Get dashboard of relations of players")
    @GetMapping("/{playerId}/relations/dashboard")
    fun getDashboard(@PathVariable playerId: Long): PlayerRelationsDashboard {
        val dashboard = service.getDashboard(playerId)

        if (dashboard.isEmpty()) {
            return PlayerRelationsDashboard()
        }

        return PlayerRelationsDashboard(dashboard[0], dashboard[1], dashboard[2])
    }

}