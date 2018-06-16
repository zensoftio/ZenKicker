package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.PageRequest
import com.kicker.domain.PageResponse
import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.PlayerDto
import com.kicker.domain.model.player.UpdateDataPlayerRequest
import com.kicker.domain.model.player.UpdatePasswordPlayerRequest
import com.kicker.model.Player
import com.kicker.service.PlayerService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    fun createPlayer(@Valid @RequestBody createPlayerRequest: CreatePlayerRequest): PlayerDto =
            PlayerDto(service.create(createPlayerRequest))

    @PutMapping
    fun updateDataPlayer(@CurrentPlayer currentPlayer: Player,
                         @Valid @RequestBody updateDataPlayerRequest: UpdateDataPlayerRequest): PlayerDto =
            PlayerDto(service.updateData(currentPlayer, updateDataPlayerRequest))


    @PutMapping("/password-reset")
    fun updatePasswordPlayer(@CurrentPlayer currentPlayer: Player,
                             @Valid @RequestBody updatePasswordPlayerRequest: UpdatePasswordPlayerRequest): PlayerDto =
            PlayerDto(service.updatePassword(currentPlayer, updatePasswordPlayerRequest))

}