package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.PageResponse
import com.kicker.domain.model.player.*
import com.kicker.exception.controller.MultipartFileException
import com.kicker.model.Player
import com.kicker.service.GameService
import com.kicker.service.PlayerService
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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

    @GetMapping("/current")
    fun getCurrent(@CurrentPlayer currentPlayer: Player): PlayerDto {
        return PlayerDto(service.get(currentPlayer.id), gameService.countByPlayer(currentPlayer.id),
                gameService.countFor10WeeksByPlayer(currentPlayer.id))
    }

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

    @PutMapping("/username")
    fun updateUsername(@CurrentPlayer currentPlayer: Player, @Valid @RequestBody request: UpdatePlayerUsernameRequest): PlayerDto {
        val player = service.updateUsername(currentPlayer.id, request)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countFor10WeeksByPlayer(player.id))
    }

    @PutMapping("/icon")
    fun updateUsername(@CurrentPlayer currentPlayer: Player, @RequestPart("file") icon: MultipartFile): PlayerDto {
        validateIcon(icon)

        val player = service.updateIcon(currentPlayer.id, icon)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countFor10WeeksByPlayer(player.id))
    }

    @PutMapping("/password")
    fun updatePassword(@CurrentPlayer currentPlayer: Player, @Valid @RequestBody request: UpdatePlayerPasswordRequest): PlayerDto {
        val player = service.updatePassword(currentPlayer.id, request)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countFor10WeeksByPlayer(player.id))
    }

    private fun validateIcon(icon: MultipartFile) {
        if (icon.isEmpty) {
            throw MultipartFileException("The uploaded file is empty")
        }

        if (icon.contentType != IMAGE_JPEG_VALUE && icon.contentType != IMAGE_PNG_VALUE
                && icon.contentType != IMAGE_GIF_VALUE) {
            throw MultipartFileException("Incorrect file type, ${icon.contentType}")
        }
    }

}