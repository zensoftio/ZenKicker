package com.kicker.service

import com.kicker.model.DashboardRating
import com.kicker.model.Player
import com.kicker.repository.DashboardRatingRepository
import org.apache.commons.collections4.CollectionUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultDashboardRatingService(
        private val repository: DashboardRatingRepository,
        private val playerService: PlayerService
) : DefaultBaseService<DashboardRating, DashboardRatingRepository>(repository), DashboardRatingService {

    override fun getAllByPlayer(player: Player): List<DashboardRating> =
            repository.findByPlayerOrderByWeeksAgoDesc(player)

    override fun getAllByLastWeek(): List<DashboardRating> = repository.findByWeeksAgoOrderByDeltaDesc(0)

    @Transactional
    override fun recalculate(player: Player) {
        val dashboardRatings = getAllByPlayer(player) as MutableList<DashboardRating>

        val rating = calculate(dashboardRatings)
        val delta = player.currentRating - rating
        val newDashboardRating = DashboardRating(player, delta)

        if (CollectionUtils.isNotEmpty(dashboardRatings)) {
            if (CollectionUtils.size(dashboardRatings) == DashboardRating.WEEKS_RATED) {
                repository.delete(dashboardRatings.first())
                dashboardRatings.remove(dashboardRatings.first())
            }
            dashboardRatings.forEach { it.weeksAgo++ }
            repository.saveAll(dashboardRatings)
            repository.flush()
        }

        repository.save(newDashboardRating)

        dashboardRatings.add(newDashboardRating)
        val newRating = calculate(dashboardRatings)

        playerService.updateRating(player.id, newRating)
    }

    private fun calculate(dashboardRatings: List<DashboardRating>): Double =
            Player.INITIAL_RATING + dashboardRatings.sumByDouble { it.getObsolescenceDelta() }

}
