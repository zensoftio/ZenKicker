package io.zensoft.kicker.service

import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.domain.model.playerStats.PlayerStatsPageRequest
import io.zensoft.kicker.domain.model.playerStats.PlayerStatsPageRequest.Companion.RATING_FIELD
import io.zensoft.kicker.domain.model.playerStats.PlayersDashboard
import io.zensoft.kicker.model.Player
import io.zensoft.kicker.model.PlayerStats
import io.zensoft.kicker.repository.PlayerStatsRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.event.EventListener
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DefaultPlayerStatsService(
        private val playerService: PlayerService,
        private val repository: PlayerStatsRepository,
        private val playerToGameService: PlayerToGameService,
        private val gameService: GameService
) : DefaultBaseService<PlayerStats, PlayerStatsRepository>(repository), PlayerStatsService {

    @EventListener
    @Transactional
    fun createPlayer(player: Player) {
        repository.save(PlayerStats(player))
    }

    override fun getByPlayer(playerId: Long): PlayerStats {
        val player = playerService.get(playerId)
        return repository.findByPlayer(player)
    }

    @Cacheable("playersDashboard")
    override fun getDashboard(): PlayersDashboard {
        val pageRequest = PlayerStatsPageRequest().apply { limit = 0; sortBy = RATING_FIELD }

        if (getAllActive(pageRequest).totalElements < 4) {
            return PlayersDashboard()
        }

        val loser = getAllActive(pageRequest.apply { limit = 1; sortBy = RATING_FIELD })
        val top3 = getAllActive(pageRequest.apply { limit = 3; sortBy = RATING_FIELD; sortDirection = DESC })

        return PlayersDashboard(
                top3.content[0].player,
                top3.content[1].player,
                top3.content[2].player,
                loser.content[0].player
        )
    }

    @Cacheable("statsPlayers")
    override fun getAll(pageRequest: PageRequest): Page<PlayerStats> = super.getAll(pageRequest)

    @Cacheable("statsActivePlayers")
    override fun getAllActive(pageRequest: PageRequest): Page<PlayerStats> = repository.findAllByActiveTrue(pageRequest)

    @CacheEvict("statsPlayers", "statsActivePlayers", allEntries = true)
    @Transactional
    override fun updateActivity(playerId: Long, active: Boolean): PlayerStats {
        val playerStats = getByPlayer(playerId)

        playerStats.active = active

        return repository.save(playerStats)
    }

    @CacheEvict("playersDashboard", "statsPlayers", "statsActivePlayers", allEntries = true)
    @Transactional
    override fun updateRatingAndRated(player: Player): PlayerStats {
        val stats = getByPlayer(player.id)

        stats.rating = playerToGameService.getActualRatingByPlayer(player.id)
        stats.rated = gameService.countDuring10WeeksByPlayer(player.id).toInt()

        return repository.save(stats)
    }

}