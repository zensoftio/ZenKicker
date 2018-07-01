package com.kicker.service

import com.kicker.model.Award
import com.kicker.model.DashboardRating
import com.kicker.model.Player
import com.kicker.model.dictionary.AwardDegree
import com.kicker.model.dictionary.AwardType
import com.kicker.repository.AwardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultAwardService(
        private val repository: AwardRepository,
        private val playerService: PlayerService,
        private val dashboardRatingService: DashboardRatingService
) : DefaultBaseService<Award, AwardRepository>(repository), AwardService {

    companion object {
        private const val TOP_PLACES = 3
    }


    override fun getAllByPlayer(player: Player): List<Award> {
        val awards = repository.findByPlayer(player)
        Collections.sort(awards, AwardsComparator())
        return awards
    }

    @Transactional
    override fun doAwardMaxRatingForWeek() {
        val players = playerService.getAll()

        if (players.size > TOP_PLACES) {
            saveAwardOverallRatingForWeek(players[0], AwardDegree.GOLD)
            saveAwardOverallRatingForWeek(players[1], AwardDegree.SILVER)
            saveAwardOverallRatingForWeek(players[2], AwardDegree.BRONZE)
        }
    }

    @Transactional
    override fun doAwardMaxDeltaRatingForWeek() {
        val dashboardRatings = dashboardRatingService.getAllByLastWeek()

        if (dashboardRatings.size > TOP_PLACES) {
            saveAwardMaxRatingDeltaForWeek(dashboardRatings[0], AwardDegree.GOLD)
            saveAwardMaxRatingDeltaForWeek(dashboardRatings[1], AwardDegree.SILVER)
            saveAwardMaxRatingDeltaForWeek(dashboardRatings[2], AwardDegree.BRONZE)
        }
    }

    private fun saveAwardOverallRatingForWeek(player: Player, awardDegree: AwardDegree) {
        val award = Award(player, AwardType.WEEK_END_OVERALL_RATING.getId(), awardDegree.getId(),
                player.currentRating.toInt().toString())

        repository.save(award)
    }

    private fun saveAwardMaxRatingDeltaForWeek(dashboardRating: DashboardRating, awardDegree: AwardDegree) {
        val award = Award(dashboardRating.player, AwardType.WEEK_MAX_RATING_DELTA.getId(), awardDegree.getId(),
                dashboardRating.delta.toInt().toString())

        repository.save(award)
    }

    private class AwardsComparator : Comparator<Award> {
        override fun compare(o1: Award, o2: Award): Int {
            val result = o1.getAwardType().getId().compareTo(o2.getAwardType().getId())
            if (0 == result) {
                return o1.getAwardDegree().getId().compareTo(o2.getAwardDegree().getId())
            }

            return result
        }
    }

}