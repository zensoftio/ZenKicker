package com.kicker.api.service

import com.kicker.api.domain.PageRequest
import com.kicker.api.model.PlayerStats
import com.kicker.api.repository.PlayerStatsRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DefaultPlayerStatsService(
        private val playerService: PlayerService,
        private val repository: PlayerStatsRepository
) : DefaultBaseService<PlayerStats, PlayerStatsRepository>(repository), PlayerStatsService {

    override fun getByPlayer(playerId: Long): PlayerStats {
        val player = playerService.get(playerId)
        return repository.findByPlayer(player) ?: save(PlayerStats(player))
    }

    @Cacheable("statsActivePlayers")
    override fun getAllActive(pageRequest: PageRequest): Page<PlayerStats> = repository.findAllByActiveTrue(pageRequest)

    @CacheEvict("statsActivePlayers", allEntries = true)
    @Transactional
    override fun updateActivity(playerId: Long, active: Boolean): PlayerStats {
        val playerStats = getByPlayer(playerId)
        playerStats.active = active

        return super.save(playerStats)
    }

}