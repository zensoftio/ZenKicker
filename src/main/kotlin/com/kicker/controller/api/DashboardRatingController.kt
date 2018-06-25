package com.kicker.controller.api

import com.kicker.domain.model.dashboard.DashboardRatingDto
import com.kicker.domain.model.dashboard.DashboardResponse
import com.kicker.service.DashboardRatingService
import com.kicker.service.PlayerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Yauheni Efimenko
 */
@RestController
@RequestMapping("/api/dashboard-rating")
class DashboardRatingController(
        private val service: DashboardRatingService,
        private val playerService: PlayerService
) {

    @GetMapping("/{playerId}")
    fun getAllByPlayer(@PathVariable playerId: Long): DashboardResponse {
        val player = playerService.get(playerId)
        return DashboardResponse(service.getAllByPlayer(player).map { DashboardRatingDto(it) })
    }

}