package com.kicker.controller.api

import com.kicker.domain.PageResponse
import com.kicker.domain.model.award.AwardDto
import com.kicker.service.AwardService
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
        private val service: AwardService
) {

    @GetMapping("/{playerId}")
    fun getAllByPlayer(@PathVariable playerId: Long): PageResponse<AwardDto> =
            PageResponse(service.getAllByPlayer(playerId).map { AwardDto(it) })

}