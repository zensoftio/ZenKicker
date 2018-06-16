package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.PageRequest
import com.kicker.domain.PageResponse
import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.PlayerDto
import com.kicker.domain.model.player.UpdatePlayerRequest
import com.kicker.model.Player
import com.kicker.service.PlayerService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/players")
class PlayerController(
        private val service: PlayerService,
        private val passwordEncoder: PasswordEncoder
) {

    @GetMapping("/current")
    fun getCurrent(@CurrentPlayer currentPlayer: Player): PlayerDto = PlayerDto(currentPlayer)

    @GetMapping("/{userId}")
    fun get(@PathVariable userId: Long): PlayerDto = PlayerDto(service.get(userId))

    @GetMapping
    fun getAll(pageRequest: PageRequest): PageResponse<PlayerDto> =
            PageResponse(service.getAll(pageRequest).map { PlayerDto(it) })

    @PostMapping
    fun createPlayer(@Valid @RequestBody createPlayerRequest: CreatePlayerRequest): PlayerDto {
        createPlayerRequest.password = passwordEncoder.encode(createPlayerRequest.password)

        return PlayerDto(service.save(createPlayerRequest))
    }

    @PutMapping
    fun updatePlayer(@CurrentPlayer currentPlayer: Player, @Valid @RequestBody updatePlayerRequest: UpdatePlayerRequest) {
        TODO()
    }

}