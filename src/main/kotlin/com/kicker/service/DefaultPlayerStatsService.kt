package com.kicker.service

import com.kicker.model.PlayerStats
import com.kicker.repository.PlayerStatsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerStatsService(
        private val repository: PlayerStatsRepository,
        private val gameService: GameService,
        private val playerService: PlayerService
) : DefaultBaseService<PlayerStats, PlayerStatsRepository>(repository), PlayerStatsService {

    override fun getByGame(gameId: Long): List<PlayerStats> {
        val game = gameService.get(gameId)
        return repository.findByGame(game)
    }

    override fun getByPlayer(playerId: Long): List<PlayerStats> {
        val player = playerService.get(playerId)
        return repository.findByPlayer(player)
    }

}