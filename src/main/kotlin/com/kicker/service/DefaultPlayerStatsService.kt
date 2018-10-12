package com.kicker.service

import com.kicker.config.property.GamesSettingsProperties
import com.kicker.config.property.PlayerSettingsProperties
import com.kicker.domain.PageRequest
import com.kicker.model.Player
import com.kicker.model.PlayerStats
import com.kicker.repository.PlayerStatsRepository
import com.kicker.utils.DateUtils
import com.kicker.utils.RatingUtils
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerStatsService(
        private val repository: PlayerStatsRepository,
        private val playerService: PlayerService,
        private val playerSettingsProperties: PlayerSettingsProperties,
        private val gamesSettingsProperties: GamesSettingsProperties
) : DefaultBaseService<PlayerStats, PlayerStatsRepository>(repository), PlayerStatsService {

    override fun getByPlayer(playerId: Long, pageRequest: PageRequest): Page<PlayerStats> {
        val player = playerService.get(playerId)
        return repository.findByPlayer(player, pageRequest)
    }

    override fun getDeltaByPlayerAndWeeksAgo(playerId: Long, weeksAgo: Long): Double {
        val player = playerService.get(playerId)
        val dates = DateUtils.getIntervalDatesOfWeek(weeksAgo)

        return repository.calculateDeltaByPlayerAndIntervalDates(player, dates.first, dates.second)
    }

    override fun getActualRatingByPlayer(playerId: Long): Double {
        var rating = Player.PLAYER_RATING

        for (i in 0..playerSettingsProperties.countWeeks!!) {
            val deltaForWeek = getDeltaByPlayerAndWeeksAgo(playerId, i)
            val obsolescenceDeltaForWeek = RatingUtils.getObsolescenceDelta(deltaForWeek,
                    playerSettingsProperties.countWeeks!!, i)

            rating += obsolescenceDeltaForWeek
        }

        return rating
    }

    override fun countLossesByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)
        return repository.countByPlayerAndWon(player, false)
    }

    override fun countWinsByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)
        return repository.countByPlayerAndWon(player, true)
    }

    override fun countGoalsAgainstByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)

        val countLosses = repository.countByPlayerAndWon(player, false)
        return repository.countGoalsByPlayerAndWon(player, true) + countLosses * gamesSettingsProperties.defaultMaxScore!!
    }

    override fun countGoalsForByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)

        val countWins = repository.countByPlayerAndWon(player, true)
        return repository.countGoalsByPlayerAndWon(player, false) + countWins * gamesSettingsProperties.defaultMaxScore!!
    }

}