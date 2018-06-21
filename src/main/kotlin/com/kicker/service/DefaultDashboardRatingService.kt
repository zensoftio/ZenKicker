package com.kicker.service

import com.kicker.model.DashboardRating
import com.kicker.model.Player
import com.kicker.repository.DashboardRatingRepository
import com.kicker.repository.PlayerRepository
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
        private val playerRepository: PlayerRepository
) : DefaultBaseService<DashboardRating, DashboardRatingRepository>(repository), DashboardRatingService {

    override fun getAllByPlayer(player: Player): List<DashboardRating> =
            repository.findByPlayerOrderByWeeksAgoDesc(player)

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
        playerRepository.save(player.apply { currentRating = calculate(dashboardRatings) })
    }

    private fun calculate(dashboardRatings: List<DashboardRating>): Double =
            Player.INITIAL_RATING + dashboardRatings.sumByDouble { it.getObsolescenceDelta() }

}
