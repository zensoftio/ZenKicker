package com.kicker.api.service

import com.kicker.api.config.property.PlayerSettingsProperties
import com.kicker.api.domain.PageRequest
import com.kicker.api.model.PlayerStats
import com.kicker.api.model.PlayerToGame
import com.kicker.api.repository.PlayerToGameRepository
import com.kicker.api.utils.DateUtils
import com.kicker.api.utils.RatingUtils
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
) : DefaultBaseService<PlayerToGame, PlayerToGameRepository>(repository), PlayerToGameService {

    override fun getPlayerGames(playerId: Long, pageRequest: PageRequest): Page<PlayerToGame> {
        val player = playerService.get(playerId)
        return repository.findByPlayer(player, pageRequest)
    }

    /*
    * Current week is number 0, so 10 week is number 9
    * */
    override fun getDeltaPerWeekDuring10WeeksByPlayer(playerId: Long): List<Double> {
        val dashboard = mutableListOf<Double>()
        for (weeksAgo in 9 downTo 0) {
            dashboard.add(getDeltaByPlayerAndWeeksAgo(playerId, weeksAgo.toLong()))
        }
        return dashboard
    }

    override fun getActualRatingByPlayer(playerId: Long): Double {
        var rating = PlayerStats.PLAYER_RATING

        for (i in 0..playerSettingsProperties.countWeeks!!) {
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