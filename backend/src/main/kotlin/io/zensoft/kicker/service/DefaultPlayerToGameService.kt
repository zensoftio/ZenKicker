package io.zensoft.kicker.service

import io.zensoft.kicker.config.property.PlayerSettingsProperties
import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.model.PlayerStats
import io.zensoft.kicker.model.PlayerToGame
import io.zensoft.kicker.repository.PlayerToGameRepository
import io.zensoft.kicker.utils.DateUtils
import io.zensoft.kicker.utils.RatingUtils
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerToGameService(
        private val repository: PlayerToGameRepository,
        private val playerService: PlayerService,
        private val playerSettingsProperties: PlayerSettingsProperties
) : DefaultBaseService<PlayerToGame>(repository), PlayerToGameService {

    override fun getPlayerGames(playerId: Long, pageRequest: PageRequest): Page<PlayerToGame> {
        val player = playerService.get(playerId)
        return repository.findByPlayer(player, pageRequest)
    }

    /**
     * Current week is number 0, so 10 week is number 9
     */
    @Cacheable("deltaPerWeekDuring10Weeks")
    override fun getDeltaPerWeekDuring10WeeksByPlayer(playerId: Long): List<Double> {
        val dashboard = mutableListOf<Double>()
        for (weeksAgo in 9 downTo 0L) {
            dashboard.add(getDeltaByPlayerAndWeeksAgo(playerId, weeksAgo))
        }
        return dashboard
    }

    override fun getActualRatingByPlayer(playerId: Long): Double {
        var rating = PlayerStats.PLAYER_RATING

        for (i in 0 until playerSettingsProperties.countWeeks!!) {
            val deltaForWeek = getDeltaByPlayerAndWeeksAgo(playerId, i)
            val obsolescenceDeltaForWeek = RatingUtils.getObsolescenceDelta(deltaForWeek,
                    playerSettingsProperties.countWeeks!!, i)

            rating += obsolescenceDeltaForWeek
        }

        return rating
    }

    private fun getDeltaByPlayerAndWeeksAgo(playerId: Long, weeksAgo: Long): Double {
        val player = playerService.get(playerId)
        val dates = DateUtils.getIntervalDatesOfWeek(weeksAgo)

        return repository.calculateDeltaByPlayerAndIntervalDates(player, dates.first, dates.second)
    }

}
