package com.kicker.service

import com.kicker.domain.PageRequest
import com.kicker.model.PlayerStats
import com.kicker.repository.PlayerStatsRepository
import com.kicker.utils.DateUtils
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
        private val playerService: PlayerService
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

}