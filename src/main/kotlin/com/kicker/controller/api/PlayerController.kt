package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.player.*
import com.kicker.service.GameService
import com.kicker.service.PlayerService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/players")
class PlayerController(
        private val service: PlayerService,
        private val gameService: GameService
) {

    @GetMapping("/{playerId}")
    fun get(@PathVariable playerId: Long): PlayerDto {
        return PlayerDto(service.get(playerId), gameService.countByPlayer(playerId),
                gameService.countFor10WeeksByPlayer(playerId))
    }

    @GetMapping
    fun getAll(pageRequest: PlayerPageRequest): PageResponse<PlayerDto> {
        return PageResponse(service.getAll(pageRequest).map {
            PlayerDto(it, gameService.countByPlayer(it.id), gameService.countFor10WeeksByPlayer(it.id))
        })
    }

    @GetMapping("/active")
    fun getAllActive(pageRequest: PlayerPageRequest): PageResponse<PlayerDto> {
        return PageResponse(service.getAllActive(pageRequest).map {
            PlayerDto(it, gameService.countByPlayer(it.id), gameService.countFor10WeeksByPlayer(it.id))
        })
    }

    @PostMapping
    fun create(@Valid @RequestBody request: CreatePlayerRequest): PlayerDto {
        val player = service.create(request)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countFor10WeeksByPlayer(player.id))
    }

    @PutMapping("/{playerId}/username")
    fun updateUsername(@PathVariable playerId: Long, @Valid @RequestBody request: UpdatePlayerUsernameRequest): PlayerDto {
        val player = service.updateUsername(playerId, request)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countFor10WeeksByPlayer(player.id))
    }

    @PutMapping("/{playerId}/password")
    fun updatePassword(@PathVariable playerId: Long, @Valid @RequestBody request: UpdatePlayerPasswordRequest): PlayerDto {
        val player = service.updatePassword(playerId, request)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countFor10WeeksByPlayer(player.id))
    }

}