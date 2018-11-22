package com.kicker.api.controller.api

import com.kicker.api.domain.PageResponse
import com.kicker.api.domain.model.playerRelations.PlayerRelationsDto
import com.kicker.api.domain.model.playerRelations.PlayerRelationsPageRequest
import com.kicker.api.service.PlayerRelationsService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

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
    fun getAllByPlayer(@PathVariable playerId: Long, @ApiIgnore pageRequest: PlayerRelationsPageRequest): PageResponse<PlayerRelationsDto> {
        return PageResponse(service.getAllByPlayer(playerId, pageRequest).map { PlayerRelationsDto(it) })
    }

}