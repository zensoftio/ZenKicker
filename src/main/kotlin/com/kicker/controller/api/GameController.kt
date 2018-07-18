package com.kicker.controller.api

import com.kicker.domain.model.game.GameDto
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.service.GameService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/games")
class GameController(
        private val service: GameService
) {

    @PostMapping("/{playerId}")
    fun gameRegistration(@PathVariable playerId: Long, @Valid @RequestBody request: GameRegistrationRequest): GameDto {
        return GameDto(service.gameRegistration(playerId, request))
    }

}