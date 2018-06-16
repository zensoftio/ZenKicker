package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.PageRequest
import com.kicker.domain.PageResponse
import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.PlayerDto
import com.kicker.domain.model.player.UpdatePlayerRequest
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
    fun get(@PathVariable userId: Long): PlayerDto = PlayerDto(service.get(userId))

    @GetMapping
    fun getAll(pageRequest: PageRequest): PageResponse<PlayerDto> =
            PageResponse(service.getAll(pageRequest).map { PlayerDto(it) })

    @PostMapping
    fun createPlayer(createPlayerRequest: CreatePlayerRequest) {

    }

    @PostMapping("/{userId}")
    fun updatePlayer(@PathVariable userId: Long, updatePlayerRequest: UpdatePlayerRequest) {

    }

}