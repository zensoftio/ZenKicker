package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.model.CreatePlayerRequest
import com.kicker.domain.model.PlayerDto
import com.kicker.domain.model.UpdatePlayerRequest
import com.kicker.model.Player
import com.kicker.service.PlayerService
import org.springframework.web.bind.annotation.*

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/players")
class PlayerController(
        private val service: PlayerService
) {

    @GetMapping("/current")
    fun getCurrent(@CurrentPlayer currentPlayer: Player): PlayerDto = PlayerDto(currentPlayer)

    @GetMapping("/{userId}")
    fun get(@PathVariable userId: Long, createPlayerRequest: CreatePlayerRequest): PlayerDto = PlayerDto(service.get(userId))

    @PostMapping
    fun createPlayer(createPlayerRequest: CreatePlayerRequest) {

    }

    @PostMapping("/{userId}")
    fun updatePlayer(@PathVariable userId: Long, updatePlayerRequest: UpdatePlayerRequest) {

    }

}