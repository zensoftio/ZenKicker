package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.PageResponse
import com.kicker.domain.model.player.*
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

    @GetMapping("/{playerId}")
    fun get(@PathVariable playerId: Long): PlayerDto = PlayerDto(service.get(playerId))

    @GetMapping
    fun getAll(@Valid pageRequest: PlayerPageRequest): PageResponse<PlayerDto> =
            PageResponse(service.getAll(pageRequest).map { PlayerDto(it) })

    @PostMapping
    fun createPlayer(@Valid @RequestBody request: CreatePlayerRequest): PlayerDto =
            PlayerDto(service.create(request))

    @PutMapping
    fun updateDataPlayer(@CurrentPlayer currentPlayer: Player,
                         @Valid @RequestBody request: UpdateDataPlayerRequest): PlayerDto =
            PlayerDto(service.updateData(currentPlayer.id, request))


    @PutMapping("/password-reset")
    fun updatePasswordPlayer(@CurrentPlayer currentPlayer: Player,
                             @Valid @RequestBody request: UpdatePasswordPlayerRequest): PlayerDto =
            PlayerDto(service.updatePassword(currentPlayer.id, request))

}