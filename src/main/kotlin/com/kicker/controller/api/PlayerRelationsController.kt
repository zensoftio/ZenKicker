package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.playerRelations.PlayerRelationsDto
import com.kicker.domain.model.playerRelations.PlayerRelationsPageRequest
import com.kicker.service.PlayerRelationsService
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

    @ApiOperation(value = "getAllByPlayer - Remember! You can use player relations page request")
    @GetMapping("/{playerId}/relations")
    fun getAllByPlayer(@PathVariable playerId: Long, @ApiIgnore pageRequest: PlayerRelationsPageRequest): PageResponse<PlayerRelationsDto> {
        return PageResponse(service.getAllByPlayer(playerId, pageRequest).map { PlayerRelationsDto(it) })
    }

}