package com.kicker.controller.api

import com.kicker.annotation.CurrentPlayer
import com.kicker.domain.PageResponse
import com.kicker.domain.model.player.*
import com.kicker.exception.controller.MultipartFileException
import com.kicker.model.Player
import com.kicker.service.GameService
import com.kicker.service.PlayerService
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType.*
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import springfox.documentation.annotations.ApiIgnore
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

    @ApiOperation(value = "Get current player")
    @GetMapping("/current")
    fun getCurrent(@ApiIgnore authentication: Authentication): PlayerDto {
        val player = authentication.principal as Player
        return PlayerDto(service.get(player.id), gameService.countByPlayer(player.id),
                gameService.countDuring10WeeksByPlayer(player.id))
    }

    @ApiOperation(value = "Get player by player`s id")
    @GetMapping("/{playerId}")
    fun get(@PathVariable playerId: Long): PlayerDto {
        return PlayerDto(service.get(playerId), gameService.countByPlayer(playerId),
                gameService.countDuring10WeeksByPlayer(playerId))
    }

    @ApiOperation(value = "Get all players", notes = """Pageable.
        * sortBy - [id,rating], default:rating
        * sortDirection - [ASC,DESC], default:DESC
    """)
    @GetMapping
    fun getAll(@ApiIgnore pageRequest: PlayerPageRequest): PageResponse<PlayerDto> {
        return PageResponse(service.getAll(pageRequest).map {
            PlayerDto(it, gameService.countByPlayer(it.id), gameService.countDuring10WeeksByPlayer(it.id))
        })
    }

    @ApiOperation(value = "Get all active players", notes = """Pageable.
        * sortBy - [id,rating], default:rating
        * sortDirection - [ASC,DESC], default:DESC
    """)
    @GetMapping("/active")
    fun getAllActive(@ApiIgnore pageRequest: PlayerPageRequest): PageResponse<PlayerDto> {
        return PageResponse(service.getAllActive(pageRequest).map {
            PlayerDto(it, gameService.countByPlayer(it.id), gameService.countDuring10WeeksByPlayer(it.id))
        })
    }

    @ApiOperation(value = "Create player")
    @PostMapping
    fun create(@Valid @RequestBody request: CreatePlayerRequest): PlayerDto {
        val player = service.create(request)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countDuring10WeeksByPlayer(player.id))
    }

    @ApiOperation(value = "Update username of player")
    @PutMapping("/username")
    fun updateUsername(@CurrentPlayer currentPlayer: Player, @Valid @RequestBody request: UpdatePlayerUsernameRequest): PlayerDto {
        val player = service.updateUsername(currentPlayer.id, request)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countDuring10WeeksByPlayer(player.id))
    }

    @ApiOperation(value = "Update icon of player")
    @PutMapping("/icon")
    fun updateUsername(@CurrentPlayer currentPlayer: Player, @RequestPart("file") icon: MultipartFile): PlayerDto {
        validateIcon(icon)

        val player = service.updateIcon(currentPlayer.id, icon)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countDuring10WeeksByPlayer(player.id))
    }

    @ApiOperation(value = "Update password of player")
    @PutMapping("/password")
    fun updatePassword(@CurrentPlayer currentPlayer: Player, @Valid @RequestBody request: UpdatePlayerPasswordRequest): PlayerDto {
        val player = service.updatePassword(currentPlayer.id, request)
        return PlayerDto(player, gameService.countByPlayer(player.id),
                gameService.countDuring10WeeksByPlayer(player.id))
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