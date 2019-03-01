package io.zensoft.kicker.controller.api

import io.swagger.annotations.ApiOperation
import io.zensoft.kicker.domain.model.achievements.AchievementDto
import io.zensoft.kicker.service.AchievementService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Yauheni Efimenko
 */

@RestController
@RequestMapping("/api/players")
class AchievementController(
        private val achievementService: AchievementService
) {

    @ApiOperation("Get achievements of player")
    @GetMapping("/{playerId}/achievements")
    fun getAchievements(@PathVariable playerId: Long): List<AchievementDto> {
        val achievements = achievementService.getAllByPlayer(playerId)
        return achievements.map { AchievementDto(it) }
    }

}