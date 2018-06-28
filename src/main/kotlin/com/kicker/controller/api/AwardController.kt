package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.award.AwardDto
import com.kicker.service.AwardService
import com.kicker.service.PlayerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/awards")
class AwardController(
        private val service: AwardService,
        private val playerService: PlayerService
) {

    @GetMapping("/{playerId}")
    fun getAllByPlayer(@PathVariable playerId: Long): PageResponse<AwardDto> {
        val player = playerService.get(playerId)
        val awards = service.getAllByPlayer(player)
        return PageResponse(awards.size.toLong(), awards.map { AwardDto(it) })
    }

}