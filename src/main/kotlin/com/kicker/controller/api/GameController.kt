package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.model.game.GameDto
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.model.Player
import com.kicker.service.GameService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/games")
class GameController(
        private val service: GameService
) {

    @PostMapping
    fun gameRegistration(@CurrentPlayer currentPlayer: Player,
                         @Valid @RequestBody request: GameRegistrationRequest): GameDto =
            GameDto(service.gameRegistration(currentPlayer, request))

}