package io.zensoft.kicker.component.schedule

import io.zensoft.kicker.model.dictionary.AchievementLevel.*
import io.zensoft.kicker.model.dictionary.AchievementType.WEEK_END_OVERALL_RATING
import io.zensoft.kicker.service.AchievementService
import io.zensoft.kicker.service.PlayerStatsService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerAchievementsSchedule(
        private val achievementService: AchievementService,
        private val playerStatsService: PlayerStatsService
) {

    @Scheduled(cron = "0 10 0 * * MON")
    @Transactional
    fun schedulePlayersAchievements() {
        val dashboard = playerStatsService.getDashboard()

        if (dashboard.isEmpty()) {
            return
        }

        achievementService.achieve(dashboard[0], WEEK_END_OVERALL_RATING, GOLD)
        achievementService.achieve(dashboard[1], WEEK_END_OVERALL_RATING, SILVER)
        achievementService.achieve(dashboard[2], WEEK_END_OVERALL_RATING, BRONZE)
    }

}