package com.kicker.service

import com.kicker.config.property.GamesSettingsProperties
import com.kicker.config.property.PlayerSettingsProperties
import com.kicker.domain.PageRequest
import com.kicker.domain.model.player.PlayerDto
import com.kicker.domain.model.player_stats.PlayerStatsDto
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
        private val gameService: GameService,
        private val playerSettingsProperties: PlayerSettingsProperties,
        private val gamesSettingsProperties: GamesSettingsProperties
) : DefaultBaseService<PlayerStats, PlayerStatsRepository>(repository), PlayerStatsService {

    override fun getStatsByPlayer(playerId: Long): PlayerStatsDto = PlayerStatsDto(
            PlayerDto(
                    playerService.get(playerId),
                    gameService.countByPlayer(playerId),
                    gameService.countDuring10WeeksByPlayer(playerId)
            ),
            countLossesByPlayer(playerId),
            countWinsByPlayer(playerId),
            countGoalsAgainstByPlayer(playerId),
            countGoalsForByPlayer(playerId)
    )

    override fun getGamesStatsByPlayer(playerId: Long, pageRequest: PageRequest): Page<PlayerStats> {
        val player = playerService.get(playerId)
        return repository.findByPlayer(player, pageRequest)
    }

    /*
    * Current week is number 0, so 10 week is number 9
    * */
    override fun getDeltaPerWeekDuring10WeeksByPlayer(playerId: Long): List<Double> {
        val dashboard = mutableListOf<Double>()
        for (weeksAgo in 0..9) {
            dashboard.add(getDeltaByPlayerAndWeeksAgo(playerId, weeksAgo.toLong()))
        }
        return dashboard
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
        return repository.countGamesByPlayerAndWon(player, false)
    }

    override fun countWinsByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)
        return repository.countGamesByPlayerAndWon(player, true)
    }

    override fun countGoalsAgainstByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)

        val countLosses = repository.countGamesByPlayerAndWon(player, false)
        return repository.countGoalsByPlayerAndWon(player, true) + countLosses * gamesSettingsProperties.defaultMaxScore!!
    }

    override fun countGoalsForByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)

        val countWins = repository.countGamesByPlayerAndWon(player, true)
        return repository.countGoalsByPlayerAndWon(player, false) + countWins * gamesSettingsProperties.defaultMaxScore!!
    }

}