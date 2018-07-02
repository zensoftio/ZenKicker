package com.kicker.controller.api

import com.kicker.domain.model.dashboard.DashboardRatingDto
import com.kicker.domain.model.dashboard.DashboardResponse
import com.kicker.service.DashboardRatingService
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
        private val service: DashboardRatingService
) {

    @GetMapping("/{playerId}")
    fun getAllByPlayer(@PathVariable playerId: Long): DashboardResponse {
        return DashboardResponse(service.getAllByPlayer(playerId).map { DashboardRatingDto(it) })
    }

}